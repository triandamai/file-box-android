package app.trian.filebox.data.datasource

import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import android.util.Size
import app.trian.filebox.data.models.FileModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class StorageDataSource {
    //https://stackoverflow.com/questions/62782648/android-11-scoped-storage-permissions
    // in android 10 or higher MediaStore.Files. have restriction(scoped storage) only  show vide,image , and video
    @SuppressLint("InlinedApi")
    suspend fun getDocuments(appContext: Context): Flow<List<FileModel>> = flow<List<FileModel>> {
        val data = mutableListOf<FileModel>()
        val uri = MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL)

        val cursor = with(appContext) {
            contentResolver.query(
                uri!!,
                arrayOf(
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
                    OR ${MediaStore.Files.FileColumns.MIME_TYPE}=?
                """.trimIndent(), arrayOf(
                    android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension("pdf"),
                    android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension("doc"),
                    android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension("docx"),
                    android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension("ppt"),
                    android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension("pptx"),
                    android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension("json"),
                    android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension("txt"),
                ), null
            )
        }

        cursor?.use {
            it.let {

                val idColumn = it.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)
                val nameColumn =
                    it.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME)
                val mimeColumn =
                    it.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE)
                val dataColumn = it.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA)
                val sizeColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)
                val dateColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED)

                while (it.moveToNext()) {
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
        emit(data)
    }.flowOn(Dispatchers.IO)

    suspend fun getImages(appContext: Context): Flow<List<FileModel>> = flow<List<FileModel>> {
        val data = mutableListOf<FileModel>()
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val cursor = with(appContext) {
            contentResolver.query(
                uri!!,
                arrayOf(
                    MediaStore.Images.ImageColumns._ID,
                    MediaStore.Images.ImageColumns.DISPLAY_NAME,
                    MediaStore.Images.ImageColumns.MIME_TYPE,
                    MediaStore.Images.ImageColumns.DATA,
                    MediaStore.Images.ImageColumns.SIZE,
                    MediaStore.Images.ImageColumns.DATE_ADDED,
                ),
                null,
                null,
                null,
                null
            )
        }

        cursor?.use {
            it.let {
                val idColumn = it.getColumnIndexOrThrow(MediaStore.Images.ImageColumns._ID)
                val nameColumn =
                    it.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DISPLAY_NAME)
                val mimeColumn =
                    it.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.MIME_TYPE)
                val dataColumn = it.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA)
                val sizeColumn = it.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.SIZE)
                val dateColumn = it.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATE_ADDED)

                while (it.moveToNext()) {
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

            emit(data)
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getVideos(appContext: Context): Flow<List<FileModel>> = flow<List<FileModel>> {
        val data = mutableListOf<FileModel>()
        val uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI

        val cursor = with(appContext) {
            contentResolver.query(
                uri!!,
                arrayOf(
                    MediaStore.Video.VideoColumns._ID,
                    MediaStore.Video.VideoColumns.DISPLAY_NAME,
                    MediaStore.Video.VideoColumns.MIME_TYPE,
                    MediaStore.Video.VideoColumns.DATA,
                    MediaStore.Video.VideoColumns.SIZE,
                    MediaStore.Video.VideoColumns.DATE_ADDED,
                ),
                null,
                null,
                null,
                null
            )
        }

        cursor?.use {
            it.let {
                val idColumn = it.getColumnIndexOrThrow(MediaStore.Video.VideoColumns._ID)
                val nameColumn =
                    it.getColumnIndexOrThrow(MediaStore.Video.VideoColumns.DISPLAY_NAME)
                val mimeColumn =
                    it.getColumnIndexOrThrow(MediaStore.Video.VideoColumns.MIME_TYPE)
                val dataColumn = it.getColumnIndexOrThrow(MediaStore.Video.VideoColumns.DATA)
                val sizeColumn = it.getColumnIndexOrThrow(MediaStore.Video.VideoColumns.SIZE)
                val dateColumn = it.getColumnIndexOrThrow(MediaStore.Video.VideoColumns.DATE_ADDED)

                while (it.moveToNext()) {
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
                        MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id
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

            emit(data)
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getAudios(appContext: Context): Flow<List<FileModel>> = flow<List<FileModel>> {
        val data = mutableListOf<FileModel>()
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

        val cursor = with(appContext) {
            contentResolver.query(
                uri!!,
                arrayOf(
                    MediaStore.Audio.AudioColumns._ID,
                    MediaStore.Audio.AudioColumns.DISPLAY_NAME,
                    MediaStore.Audio.AudioColumns.MIME_TYPE,
                    MediaStore.Audio.AudioColumns.DATA,
                    MediaStore.Audio.AudioColumns.SIZE,
                    MediaStore.Audio.AudioColumns.DATE_ADDED,
                ),
                null,
                null,
                null,
                null
            )
        }

        cursor?.use {
            it.let {
                val idColumn = it.getColumnIndexOrThrow( MediaStore.Audio.AudioColumns._ID)
                val nameColumn =
                    it.getColumnIndexOrThrow( MediaStore.Audio.AudioColumns.DISPLAY_NAME)
                val mimeColumn =
                    it.getColumnIndexOrThrow( MediaStore.Audio.AudioColumns.MIME_TYPE)
                val dataColumn = it.getColumnIndexOrThrow( MediaStore.Audio.AudioColumns.DATA)
                val sizeColumn = it.getColumnIndexOrThrow( MediaStore.Audio.AudioColumns.SIZE)
                val dateColumn = it.getColumnIndexOrThrow( MediaStore.Audio.AudioColumns.DATE_ADDED)

                while (it.moveToNext()) {
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
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id
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

            emit(data)
        }
    }.flowOn(Dispatchers.IO)


}