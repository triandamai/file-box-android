package app.trian.filebox.data

import app.trian.filebox.data.models.FileModel
import kotlinx.coroutines.flow.Flow


interface StorageRepository {
    suspend fun getListImage(): Flow<List<FileModel>>
    suspend fun getListVideo(): Flow<List<FileModel>>
    suspend fun getAllFiles():Flow<List<FileModel>>
}