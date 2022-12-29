package app.trian.filebox.composables

import android.content.Context
import android.net.wifi.WifiManager

fun Context.getMacAddress():String{
    val wifiManager = this.getSystemService(Context.WIFI_SERVICE) as WifiManager
    val info = wifiManager.connectionInfo

    return info.macAddress
}

fun String.isValidMacAddress():Boolean = this == "02:00:00:00:00:00"