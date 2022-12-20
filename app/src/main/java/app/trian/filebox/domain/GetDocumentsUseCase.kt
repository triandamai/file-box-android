package app.trian.filebox.domain

import app.trian.filebox.data.models.DataState
import app.trian.filebox.data.repository.StorageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetDocumentsUseCase @Inject constructor(
    private val storageRepository: StorageRepository
) {

    operator fun invoke() = channelFlow {
        send(DataState.Loading)
        storageRepository.getDocumentFromDb().onEach { documents ->
            val groupedData = documents.groupBy { it.path }
            if (groupedData.isEmpty()) {
                send(DataState.Empty)
            } else {
                send(DataState.Data(groupedData))
            }
        }
            .catch {
                send(DataState.Error(it.message.orEmpty()))
            }.collect()
    }.flowOn(Dispatchers.IO)
}