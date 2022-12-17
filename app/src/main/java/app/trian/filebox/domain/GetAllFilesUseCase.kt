package app.trian.filebox.domain

import app.trian.filebox.data.repository.StorageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllFilesUseCase @Inject constructor(
    private val storageRepository: StorageRepository
) {

    suspend operator fun invoke() = flow {
        emit(storageRepository.getAllFiles())
    }.flowOn(Dispatchers.IO)
}