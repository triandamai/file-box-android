package app.trian.filebox.data.repository

import app.trian.filebox.data.datasource.local.device.Device
import app.trian.filebox.data.datasource.local.device.DeviceDao
import app.trian.filebox.data.models.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceRepositoryImpl @Inject constructor(
    private val deviceDao: DeviceDao
) : DeviceRepository {
    override suspend fun getDevices(): Flow<DataState<List<Device>>> = flow {
        emit(DataState.Loading)
        val devices = deviceDao.getListDevices()
        if (devices.isEmpty()) {
            emit(DataState.Empty)
        } else {
            emit(DataState.Data(devices))
        }
    }.catch {
        emit(DataState.Error(it.message.orEmpty()))
    }.flowOn(Dispatchers.IO)
}