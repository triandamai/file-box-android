/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.48
 */
package app.trian.filebox.feature.sendFileOverview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.trian.filebox.data.datasource.local.device.Device
import app.trian.filebox.data.models.DataState
import app.trian.filebox.data.repository.DeviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SendFileOverviewViewModel @Inject constructor(
    private val deviceRepository: DeviceRepository
) : ViewModel() {
    private val _devices = MutableStateFlow<DataState<List<Device>>>(DataState.Loading)
    val devices = _devices.asStateFlow()

    init {
        getDevices()
    }

    fun getDevices() = with(viewModelScope) {
        launch {
            deviceRepository
                .getDevices()
                .onEach {
                    _devices.tryEmit(it)
                }
                .collect()
        }
    }
}