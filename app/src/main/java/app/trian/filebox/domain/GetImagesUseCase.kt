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

class GetImagesUseCase @Inject constructor(
    private val storageRepository: StorageRepository
) {

    operator fun invoke() = channelFlow {
        send(DataState.Loading)
        storageRepository.getImagesFromDb().onEach { images ->
            val groupData = images.groupBy { it.path }
            if (groupData.isEmpty()) {
                send(DataState.Empty)
            } else {
                send(DataState.Data(groupData))
            }
        }
            .catch { send(DataState.Error(it.message.orEmpty())) }
            .collect()
    }.flowOn(Dispatchers.IO)
}