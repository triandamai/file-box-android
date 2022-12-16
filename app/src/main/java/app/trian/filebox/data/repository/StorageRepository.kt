package app.trian.filebox.data.repository

import app.trian.filebox.data.models.FileModel
import kotlinx.coroutines.flow.Flow


interface StorageRepository {
    suspend fun getAllFiles(): Map<String, List<FileModel>>
    suspend fun getImages(): Flow<List<FileModel>>
}