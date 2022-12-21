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
        storageRepository.getAllImagesFromStorage().onEach { datas ->
            val data = datas.groupBy { it.path }
            if (data.isEmpty()) {
                send(DataState.Empty)
            } else {
                send(DataState.Data(data))
            }
        }.catch { send(DataState.Error(it.message.orEmpty())) }
            .collect()
    }.flowOn(Dispatchers.IO)
}