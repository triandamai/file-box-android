package app.trian.filebox.data.repository

import app.trian.filebox.data.datasource.local.device.Device
import app.trian.filebox.data.models.DataState
import kotlinx.coroutines.flow.Flow

interface DeviceRepository {
    suspend fun getDevices(): Flow<DataState<List<Device>>>
}