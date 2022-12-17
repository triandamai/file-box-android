package app.trian.filebox.domain

import app.trian.filebox.data.repository.StorageRepository
import javax.inject.Inject

class SyncDocumentsUseCase @Inject constructor(
    private val storageRepository: StorageRepository
) {

    suspend operator fun invoke() =
        storageRepository.syncDocumentFromStorage()

}