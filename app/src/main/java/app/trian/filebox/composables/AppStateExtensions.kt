package app.trian.filebox.composables

import android.util.Log
import app.trian.filebox.BottomBarType
import app.trian.filebox.FileBoxState

fun FileBoxState.incrementSelectedFileCount(currentSize: Int=0) {
    with(this) {
        changeBottomBar(BottomBarType.PICK_FILE)
        selectedFileCount = currentSize
    }
}

fun FileBoxState.decrementSelectedFileCount(currentSize:Int = 0) {
    with(this) {
        selectedFileCount = currentSize
        Log.e("decrement",currentSize.toString())
        if (currentSize == 0) {
            changeBottomBar(BottomBarType.BASIC)
            return
        }
        changeBottomBar(BottomBarType.PICK_FILE)


    }
}