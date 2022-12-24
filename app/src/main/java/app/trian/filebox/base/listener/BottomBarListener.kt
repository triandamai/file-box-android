/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.38
 */
package app.trian.filebox.base.listener

fun interface BottomBarListener {
    fun onItemClicked(tag:ActionBottomBar)
}

enum class ActionBottomBar{
    ACTION_NOTHING,
    ACTION_FAB,
    ACTION_ITEM,
}