package app.trian.filebox.data.datasource

import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.util.Size
import app.trian.filebox.data.models.FileModel

class StorageDataSource {
    //https://stackoverflow.com/questions/62782648/android-11-scoped-storage-permissions
    // in android 10 or higher MediaStore.Files. have restriction(scoped storage) only  show vide,image , and video
    @SuppressLint("InlinedApi")
    suspend fun getAllFiles(appContext: Context): List<FileModel> {


        val data = mutableListOf<FileModel>()
        val uri = MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL)

        val cursor = with(appContext) {
            contentResolver.query(
                uri!!, arrayOf(
                    MediaStore.Files.FileColumns._ID,
                    MediaStore.Files.FileColumns.DISPLAY_NAME,
                    MediaStore.Files.FileColumns.MIME_TYPE,
                    MediaStore.Files.FileColumns.DATA,
                    MediaStore.Files.FileColumns.SIZE,
                    MediaStore.Files.FileColumns.DATE_ADDED,
                ), """
                    ${MediaStore.Files.FileColumns.MIME_TYPE}=? 
                    OR ${MediaStore.Files.FileColumns.MIME_TYPE}=?
                    OR ${MediaStore.Files.FileColumns.MIME_TYPE}=?
                    OR ${MediaStore.Files.FileColumns.MIME_TYPE}=?
                    OR ${MediaStore.Files.FileColumns.MIME_TYPE}=?
                    OR ${MediaStore.Files.FileColumns.MIME_TYPE}=?
                """.trimIndent(), arrayOf(
                    android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension("js"),
                    android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension("pdf"),
                    android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension("html"),
                    android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension("jpg"),
                    android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension("jpeg"),
                    android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension("png")
                ), null
            )
        }

        cursor?.use {
            it.let {

                val idColumn = it.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)
                val nameColumn = it.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME)
                val mimeColumn = it.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE)
                val dataColumn = it.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA)
                val sizeColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)
                val dateColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED)

                while (it.moveToNext()) {
                    Log.e("sasa3","sas")
                    val id = it.getLong(idColumn)
                    val name = it.getString(nameColumn)
                    val mime = it.getString(mimeColumn)
                    val dataPath = it.getString(dataColumn)
                    val size = it.getString(sizeColumn)
                    val date = it.getString(dateColumn)
                    var rs = ""
                    dataPath.split("/").apply {
                        val getFolder = this[this.size - 2]
                        rs = if (getFolder == "0") "Internal" else getFolder
                    }
                    val contentUri = ContentUris.withAppendedId(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id
                    )
                    // add the URI to the list
                    // generate the thumbnail
                    val thumbnail = try {

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            val widthSize = 480
                            val heightSize = 480
                            val loadThumbnail = appContext.contentResolver.loadThumbnail(
                                contentUri, Size(widthSize, heightSize), null
                            )
                            loadThumbnail
                        } else {
                            null
                        }
                    } catch (e: Exception) {
                        null
                    }

                    val fileModel = FileModel(
                        id = id,
                        name = name,
                        mime = mime,
                        size = size,
                        uri = contentUri,
                        thumb = thumbnail,
                        date = date,
                        path = rs
                    )
                    data.add(fileModel)
                }
            }

        }

        return data
    }
}