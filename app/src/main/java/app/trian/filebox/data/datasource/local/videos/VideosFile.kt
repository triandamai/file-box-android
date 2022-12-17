package app.trian.filebox.data.datasource.local.videos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "videos_file"
)
data class VideosFile(
    @PrimaryKey
    @ColumnInfo
    val uid:Long=0,
    @ColumnInfo
    var name:String="",
    @ColumnInfo
    var size:String="",
    @ColumnInfo
    var date:String="",
    @ColumnInfo
    var uri: String="",
    @ColumnInfo
    var path:String="",
    @ColumnInfo
    var mime:String=""
)