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