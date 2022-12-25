package app.trian.filebox.data.datasource.local.device

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "device"
)
data class Device(
    @PrimaryKey
    var deviceId:String,
    @ColumnInfo
    val deviceName:String,
    @ColumnInfo
    val deviceUniq:String
)
