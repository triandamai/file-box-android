package app.trian.filebox.base.listener

fun interface SnackbarBarListener {
    fun onAction(tag:ActionSnackBar)
}

enum class ActionSnackBar{
    ACTION_NOTHING,
    ACTION_SEND_FILES,
    ACTION_MORE_OPTION
}
