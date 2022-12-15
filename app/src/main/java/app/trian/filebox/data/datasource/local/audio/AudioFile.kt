package app.trian.filebox.data.datasource.local.audio

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AudioFile(
    @PrimaryKey val uid:Long,
    val firstName:String,
    var name:String,
    var size:String,
    var date:String,
    var uri: String,
    var path:String,
    var mime:String
)