package app.trian.filebox.data.models

import android.graphics.Bitmap
import android.net.Uri

data class FileModel(
    var id:Long=0,
    var name:String="",
    var size:String="",
    var date:String="",
    var uri:Uri?=null,
    var thumb:Bitmap?=null,
    var path:String = "",
    var mime:String = ""
)
