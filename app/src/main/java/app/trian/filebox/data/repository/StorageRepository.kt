package app.trian.filebox.data.repository

import app.trian.filebox.data.models.FileModel


interface StorageRepository {
    suspend fun getAllFiles(): Map<String, List<FileModel>>
}