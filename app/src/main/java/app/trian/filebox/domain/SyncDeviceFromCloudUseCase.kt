package app.trian.filebox.domain

import app.trian.filebox.data.datasource.local.device.Device
import app.trian.filebox.data.repository.DeviceRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class SyncDeviceFromCloudUseCase @Inject constructor(
    private val deviceRepository: DeviceRepository
) {
    suspend operator fun invoke() {
        deviceRepository
            .syncDeviceFromCloud()
            .onEach { it ->
                val deviceToInsert = it.filter { !it.isDelete }.map {
                    Device(
                        deviceName = it.deviceName,
                        deviceUniq = it.deviceUnique,
                        deviceId = it.deviceId
                    )
                }

                val deviceToDelete = it.filter { it.isDelete }.map {
                    Device(
                        deviceName = it.deviceName,
                        deviceUniq = it.deviceUnique,
                        deviceId = it.deviceId
                    )
                }
                deviceRepository.insertDevice(deviceToInsert)
                deviceRepository.deleteDevice(deviceToDelete)
            }
            .catch { }
            .collect()
    }
}