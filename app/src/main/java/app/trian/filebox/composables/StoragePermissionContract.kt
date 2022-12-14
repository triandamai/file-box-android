package app.trian.filebox.composables

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import androidx.core.content.ContextCompat

fun Map<String, Boolean>.isGranted(): Boolean {
    val write = this[Manifest.permission.WRITE_EXTERNAL_STORAGE]
    val read = this[Manifest.permission.READ_EXTERNAL_STORAGE]

    return if (read == null && write == null) false else (write!! && read!!)
}

val permissionReadWrite = arrayOf(
    Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.WRITE_EXTERNAL_STORAGE
)

fun Context.checkPermissionManageStorage(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        Environment.isExternalStorageManager()
    } else {
        val read = (ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) ==
                PackageManager.PERMISSION_GRANTED)
        val write = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) ==
                PackageManager.PERMISSION_GRANTED

        return (read && write)
    }
}
fun Context.checkPermissionManageStorage(
    openPermission:()->Unit,
    openIntent:(Intent)->Unit
){
    if(!this.checkPermissionManageStorage()){
        this.createIntentManageStorage {
            if(it == null){
                openPermission()
            }else{
                openIntent(it)
            }
        }
    }
}

fun Context.createIntentManageStorage(cb: (Intent?) -> Unit) {
    val ctx = this
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        try {

            Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION).apply {
                addCategory("android.intent.category.DEFAULT")
                data = Uri.parse(String.format("package:%s", ctx.packageName))
                cb(this)

            }

        } catch (e: Exception) {
            Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION).apply {
                cb(this)
            }
        }
    }else{
        cb(null)
    }
}