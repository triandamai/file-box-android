package app.trian.filebox.domain

import app.trian.filebox.data.repository.StorageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetDocumentsUseCase @Inject constructor(
    private val storageRepository: StorageRepository
) {

    suspend operator fun invoke() = channelFlow {
        storageRepository.getDocumentFromDb().onEach { documents ->
            send(documents.groupBy { it.path })
        }.collect()
    }.flowOn(Dispatchers.IO)
}