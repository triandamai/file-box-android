package app.trian.filebox.domain

import app.trian.filebox.data.datasource.local.selected.SelectedDao
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetSelectedFileIdUseCase @Inject constructor(
    private val selectedDao: SelectedDao
) {

    operator fun invoke() = channelFlow {
        selectedDao.getAll().onEach {
            send(it.map { it.uid })
        }.collect()
    }
}