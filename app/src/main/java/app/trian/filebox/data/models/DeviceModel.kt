package app.trian.filebox.data.models

data class DeviceModel(
    var deviceId: String = "",
    val deviceName: String = "",
    val deviceUnique: String = "",
    var isDelete: Boolean = false
)


fun DeviceModel.toFirestore() = mapOf(
    "deviceId" to deviceId,
    "deviceName" to deviceName,
    "deviceUnique" to deviceUnique,
    "isDelete" to isDelete
)
