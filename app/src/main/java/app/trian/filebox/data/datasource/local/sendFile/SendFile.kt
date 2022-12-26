/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.43
 */
package app.trian.filebox.data.datasource.local.sendFile

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "send_file"
)
data class SendFile(
    @PrimaryKey
    val uid:Long,
    @ColumnInfo
    val sendId:String,
    @ColumnInfo
    val deviceId:String,
    @ColumnInfo
    val createdAt:Long,
    @ColumnInfo
    val deviceName:String
)
