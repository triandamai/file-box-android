package app.trian.filebox.data

import android.content.ContentUris
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import android.util.Size
import app.trian.filebox.data.models.FileModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorageRepositoryImpl @Inject constructor(
    @ApplicationContext val appContext: Context
) : StorageRepository {
    override suspend fun getListImage(): Flow<List<FileModel>> = flow {
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI


        val projection = arrayOf(
            MediaStore.MediaColumns.DISPLAY_NAME,
            MediaStore.MediaColumns.SIZE,
            MediaStore.MediaColumns.DATE_ADDED,
            MediaStore.MediaColumns._ID
        )
        val cursor = with(appContext) {
            contentResolver.query(
                uri,
                projection,
                null,
                null,
                null

            )
        }
        val data = mutableListOf<FileModel>()
        cursor?.use {

            it.let {
                val idColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                val nameColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
                val sizeColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)
                val dateColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED)
                while (it.moveToNext()) {
                    val id = it.getLong(idColumn)
                    val name = it.getString(nameColumn)
                    val size = it.getString(sizeColumn)
                    val date = it.getString(dateColumn)
                    val contentUri = ContentUris.withAppendedId(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        id
                    )
                    // add the URI to the list
                    // generate the thumbnail
                    val thumbnail = try {

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            (this as Context).contentResolver.loadThumbnail(
                                contentUri,
                                Size(480, 480),
                                null
                            )
                        } else {
                            null
                        }
                    } catch (e: Exception) {
                        null
                    }

                    data.add(
                        FileModel(
                            name = name,
                            size = size,
                            date = date,
                            id = id,
                            thumb = thumbnail,
                            uri = contentUri,
                            path = ""
                        )
                    )


                }
            }
            emit(data)
        }
        cursor?.close();
    }.flowOn(Dispatchers.IO)

    override suspend fun getListVideo(): Flow<List<FileModel>> = flow {
        val uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.MediaColumns.DISPLAY_NAME,
            MediaStore.MediaColumns.SIZE,
            MediaStore.MediaColumns.DATE_ADDED,
            MediaStore.MediaColumns._ID,
            MediaStore.MediaColumns.MIME_TYPE
        )
        val cursor = with(appContext) {
            contentResolver.query(
                uri,
                projection,
                null,
                null,
                null

            )
        }
        val data = mutableListOf<FileModel>()
        cursor?.use {
            it.let {
                val idColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
                val nameColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
                val sizeColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)
                val dateColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_TAKEN)
                while (it.moveToNext()) {
                    val id = it.getLong(idColumn)
                    val name = it.getString(nameColumn)
                    val size = it.getString(sizeColumn)
                    val date = it.getString(dateColumn)

                    val contentUri = ContentUris.withAppendedId(
                        MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                        id
                    )
                    // add the URI to the list
                    // generate the thumbnail
                    val thumbnail = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        (this as Context).contentResolver.loadThumbnail(
                            contentUri,
                            Size(480, 480),
                            null
                        )
                    } else {
                        null
                    }

                    data.add(
                        FileModel(
                            name = name,
                            size = size,
                            date = date,
                            id = id,
                            thumb = thumbnail,
                            uri = contentUri,
                            path = ""
                        )
                    )


                }
            }
            emit(data)
        }
    }.flowOn(Dispatchers.IO)
}