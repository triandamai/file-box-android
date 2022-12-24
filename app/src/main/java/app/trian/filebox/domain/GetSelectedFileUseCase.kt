/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.45
 */
package app.trian.filebox.domain

import app.trian.filebox.data.datasource.local.selected.SelectedDao
import app.trian.filebox.data.models.DataState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetSelectedFileUseCase @Inject constructor(
    private val selectedDao: SelectedDao
) {

    operator fun invoke() = channelFlow {
        send(DataState.Loading)
        selectedDao.getAll().onEach {
            if (it.isEmpty()) {
                send(DataState.Empty)
            } else {
                send(DataState.Data(it))
            }
        }
            .catch {
                send(DataState.Error(it.message.toString()))
            }
            .collect()
    }
}