package app.trian.filebox.data.datasource.local.sendFile

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "send_file"
)
data class SendFile(
    @PrimaryKey
    var uid:Long
)
