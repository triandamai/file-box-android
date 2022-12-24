/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.38
 */
package app.trian.filebox.base.listener

fun interface SnackbarBarListener {
    fun onAction(tag:ActionSnackBar)
}

enum class ActionSnackBar{
    ACTION_NOTHING,
    ACTION_SEND_FILES,
    ACTION_MORE_OPTION
}
