/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.38
 */
package app.trian.filebox.base.listener

fun interface AppStateListener {
    fun onMessage(tag:ActionAppState,data:Map<String,String>)
}

enum class ActionAppState{
    NOTHING,
    SHOW_SELECTION,
    HIDE_SELECTION
}