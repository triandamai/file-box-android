package app.trian.filebox.data.repository

import android.content.Context
import app.trian.filebox.data.datasource.StorageDataSource
import app.trian.filebox.data.datasource.local.audio.AudioDao
import app.trian.filebox.data.datasource.local.audio.AudioFile
import app.trian.filebox.data.datasource.local.images.ImagesDao
import app.trian.filebox.data.datasource.local.images.ImagesFile
import app.trian.filebox.data.datasource.local.videos.VideosDao
import app.trian.filebox.data.datasource.local.videos.VideosFile
import app.trian.filebox.data.models.FileModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class StorageRepositoryImpl @Inject constructor(
    @ApplicationContext val appContext: Context,
    private val imagesDao: ImagesDao,
    private val videosDao: VideosDao,
    private val audioDao:AudioDao
) : StorageRepository {
    //https://stackoverflow.com/questions/62782648/android-11-scoped-storage-permissions

    override suspend fun getAllFiles(): Map<String, List<FileModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun syncImagesFromStorage() {
        StorageDataSource().getImages(appContext).onEach {
            val convert = (it.map { f ->
                ImagesFile(
                    uid = f.id,
                    name = f.name,
                    size = f.size,
                    date = f.date,
                    uri = f.uri?.path.toString(),
                    path = f.path,
                    mime = f.mime

                )
            })
            imagesDao.insertImages(
                *convert.toTypedArray()
            )
        }.collect()
    }


    override suspend fun getImagesFromDb(): Flow<List<ImagesFile>> = flow {
        val data = imagesDao.getAll()
        emit(data)
    }.flowOn(Dispatchers.IO)

    override suspend fun syncVideosFromStorage() {
        StorageDataSource().getVideos(appContext).onEach {
            val convert = (it.map { f ->
                VideosFile(
                    uid = f.id,
                    name = f.name,
                    size = f.size,
                    date = f.date,
                    uri = f.uri?.path.toString(),
                    path = f.path,
                    mime = f.mime

                )
            })
            videosDao.insertVideos(
                *convert.toTypedArray()
            )
        }.collect()
    }

    override suspend fun getVideosFromDb(): Flow<List<VideosFile>> = flow {
        val data = videosDao.getAll()
        emit(data)
    }.flowOn(Dispatchers.IO)

    override suspend fun syncAudiosFromStorage() {
        StorageDataSource().getAudios(appContext).onEach {
            val convert = (it.map { f ->
                AudioFile(
                    uid = f.id,
                    name = f.name,
                    size = f.size,
                    date = f.date,
                    uri = f.uri?.path.toString(),
                    path = f.path,
                    mime = f.mime

                )
            })
            audioDao.insertAudios(
                *convert.toTypedArray()
            )
        }.collect()
    }

    override suspend fun getAudiosFromDb(): Flow<List<AudioFile>> = flow {
        val data = audioDao.getAll()
        emit(data)
    }.flowOn(Dispatchers.IO)
}