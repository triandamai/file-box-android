package app.trian.filebox.base

fun FileBoxState.updateSelectedFileCount(currentSize: Int = 0) {
    with(this) {
        selectedFileCount = currentSize
        if (currentSize == 0) {
            showBottomBar(BottomBarType.BASIC)
            return
        }
        showBottomBar(BottomBarType.PICK_FILE)
    }
}