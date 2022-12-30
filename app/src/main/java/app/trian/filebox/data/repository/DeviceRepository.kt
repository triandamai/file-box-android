package app.trian.filebox.data.repository

import app.trian.filebox.data.datasource.local.device.Device
import app.trian.filebox.data.models.DataState
import app.trian.filebox.data.models.DeviceModel
import kotlinx.coroutines.flow.Flow

interface DeviceRepository {
    suspend fun getDeviceUniqueId():DeviceModel?

    suspend fun getDevices(): Flow<DataState<List<Device>>>

    suspend fun syncDeviceFromCloud():Flow<List<DeviceModel>>

    suspend fun insertDevice(device:List<Device>):Flow<Pair<Boolean,String>>

    suspend fun deleteDevice(device:List<Device>):Flow<Pair<Boolean,String>>
}