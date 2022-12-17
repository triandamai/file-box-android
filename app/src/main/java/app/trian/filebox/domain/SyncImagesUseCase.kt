package app.trian.filebox.domain

import app.trian.filebox.data.datasource.local.images.ImagesDao
import app.trian.filebox.data.datasource.local.images.ImagesFile
import app.trian.filebox.data.repository.StorageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class SyncImagesUseCase @Inject constructor(
    private val storageRepository: StorageRepository,
    private val imagesDao: ImagesDao
) {

    suspend operator fun invoke() = channelFlow {
        storageRepository.getImages().onEach {
            val convert = (it.map { f ->
                ImagesFile(
                    uid = f.id,
                    name = f.name,
                    size = f.size,
                    date = f.date,
                    uri = f.uri?.path.toString(),
                    path = f.path,
                    mime = f.mime

                )
            })
            imagesDao.insertImages(
                *convert.toTypedArray()
            )
            send(true)
        }.collect()
    }.flowOn(Dispatchers.IO)
}