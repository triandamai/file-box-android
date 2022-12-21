package app.trian.filebox.base

fun FileBoxState.updateSelectedFileCount(currentSize: Int = 0) {
    with(this) {
        selectedFileCount = currentSize
    }
}