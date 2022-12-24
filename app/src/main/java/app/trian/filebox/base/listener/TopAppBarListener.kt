/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.39
 */
package app.trian.filebox.base.listener

fun interface TopAppBarListener {
    fun onAction(tag:ActionTopAppBar)
}
enum class ActionTopAppBar{
    ACTION_NOTHING,
    ACTION_NAV_BACK,
    ACTION_SEND,
    ACTION_SHARE_LINK,
}