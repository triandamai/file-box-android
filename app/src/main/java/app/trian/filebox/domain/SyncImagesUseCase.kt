package app.trian.filebox.domain

import app.trian.filebox.data.repository.StorageRepository
import javax.inject.Inject

class SyncImagesUseCase @Inject constructor(
    private val storageRepository: StorageRepository
) {
    suspend operator fun invoke() = storageRepository.syncImagesFromStorage()
}