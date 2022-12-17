package app.trian.filebox.data.repository

import app.trian.filebox.data.datasource.local.images.ImagesFile
import app.trian.filebox.data.models.FileModel
import kotlinx.coroutines.flow.Flow


interface StorageRepository {
    suspend fun getAllFiles(): Map<String, List<FileModel>>
    suspend fun syncImagesFromStorage()
    suspend fun getImagesFromDb(): Flow<List<ImagesFile>>
    suspend fun getVideosFromStorage(): Flow<List<FileModel>>
}