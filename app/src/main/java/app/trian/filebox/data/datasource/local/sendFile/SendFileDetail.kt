package app.trian.filebox.data.datasource.local.sendFile

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "send_file_detail"
)
data class SendFileDetail(
    @PrimaryKey
    val id:Long,
    val sendId:String
)
