package app.trian.filebox.data.repository

import app.trian.filebox.data.datasource.local.audio.AudioFile
import app.trian.filebox.data.datasource.local.documents.DocumentFile
import app.trian.filebox.data.datasource.local.selected.SelectedFile
import app.trian.filebox.data.datasource.local.images.ImageFile
import app.trian.filebox.data.datasource.local.videos.VideosFile
import kotlinx.coroutines.flow.Flow


interface StorageRepository {
    suspend fun syncDocumentFromStorage()
    suspend fun getDocumentFromDb(): Flow<List<DocumentFile>>
    suspend fun syncImagesFromStorage()
    suspend fun getImagesFromDb(): Flow<List<ImageFile>>
    suspend fun syncVideosFromStorage()
    suspend fun getVideosFromDb(): Flow<List<VideosFile>>
    suspend fun syncAudiosFromStorage()
    suspend fun getAudiosFromDb(): Flow<List<AudioFile>>
}