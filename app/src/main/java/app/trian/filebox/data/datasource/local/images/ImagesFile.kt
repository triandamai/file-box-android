package app.trian.filebox.data.datasource.local.images

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImagesFile(
    @PrimaryKey val uid:Long=0,
    var name:String="",
    var size:String="",
    var date:String="",
    var uri: String="",
    var path:String="",
    var mime:String=""
)