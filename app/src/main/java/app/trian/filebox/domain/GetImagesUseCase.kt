package app.trian.filebox.domain

import app.trian.filebox.data.repository.StorageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(
    private val storageRepository: StorageRepository
) {

    suspend operator fun invoke() = channelFlow {
        storageRepository.getImagesFromDb().onEach { images ->
            send(images.groupBy { it.path })
        }.collect()
    }.flowOn(Dispatchers.IO)
}