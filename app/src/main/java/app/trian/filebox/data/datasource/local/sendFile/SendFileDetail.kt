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
    tableName = "send_file_detail"
)
data class SendFileDetail(
    @PrimaryKey
    val id:Long,
    @ColumnInfo
    val sendId:String,
    @ColumnInfo
    val fileId:Long,
    @ColumnInfo
    var isUploaded:Boolean,
    @ColumnInfo
    var fileName:String,
    @ColumnInfo
    var mime:String
)
