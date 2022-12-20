package app.trian.filebox.base

import android.util.Log

fun FileBoxState.incrementSelectedFileCount(currentSize: Int=0) {
    with(this) {
        showBottomBar(BottomBarType.PICK_FILE)
        selectedFileCount = currentSize
    }
}

fun FileBoxState.decrementSelectedFileCount(currentSize:Int = 0) {
    with(this) {
        selectedFileCount = currentSize
        Log.e("decrement",currentSize.toString())
        if (currentSize == 0) {
            showBottomBar(BottomBarType.BASIC)
            return
        }
        showBottomBar(BottomBarType.PICK_FILE)


    }
}