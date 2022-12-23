package app.trian.filebox.base.listener

fun interface BottomBarListener {
    fun onItemClicked(tag:ActionBottomBar)
}

enum class ActionBottomBar{
    ACTION_NOTHING,
    ACTION_FAB,
    ACTION_ITEM,
}