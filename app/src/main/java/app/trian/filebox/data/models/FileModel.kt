package app.trian.filebox.data.models

import android.graphics.Bitmap
import android.net.Uri

data class FileModel(
    var id:Long,
    var name:String,
    var size:String,
    var date:String,
    var uri:Uri,
    var thumb:Bitmap?,
    var path:String,
    var mime:String
)
