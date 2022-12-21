package app.trian.filebox.data.repository

import app.trian.filebox.data.datasource.local.selected.SelectedFile
import app.trian.filebox.data.models.FileModel
import kotlinx.coroutines.flow.Flow


interface StorageRepository {
    suspend fun getSelectedFile():Flow<List<SelectedFile>>

    suspend fun getAllImagesFromStorage(): Flow<List<FileModel>>

    suspend fun clearSelectedFile()

}