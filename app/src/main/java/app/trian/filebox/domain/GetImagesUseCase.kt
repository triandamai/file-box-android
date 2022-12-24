/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.45
 */
package app.trian.filebox.domain

import android.annotation.SuppressLint
import app.trian.filebox.data.models.DataState
import app.trian.filebox.data.repository.StorageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import java.sql.Date
import java.text.SimpleDateFormat
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(
    private val storageRepository: StorageRepository
) {

    @SuppressLint("SimpleDateFormat")
    operator fun invoke() = channelFlow {
        storageRepository.getAllImagesFromStorage().onEach { datas ->
            val data = datas.map {
                it.apply {
                    val dt = try {
                        val sdf = SimpleDateFormat("dd MMMM yyyy")
                        val netDate = Date(date.toLong() * 1000)
                        sdf.format(netDate)
                    } catch (e: Exception) {
                        e.toString()
                    }
                    date = dt
                }
            }.groupBy { it.date }
            if (data.isEmpty()) {
                send(DataState.Empty)
            } else {
                send(DataState.Data(data))
            }
        }.catch { send(DataState.Error(it.message.orEmpty())) }
            .collect()
    }.flowOn(Dispatchers.IO)
}