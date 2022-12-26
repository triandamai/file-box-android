package app.trian.filebox.base

import app.trian.filebox.base.listener.ActionAppState
import app.trian.filebox.base.listener.ActionBottomBar
import app.trian.filebox.base.listener.ActionSnackBar
import app.trian.filebox.base.listener.ActionTopAppBar
import app.trian.filebox.base.listener.AppStateListener
import app.trian.filebox.base.listener.BottomBarListener
import app.trian.filebox.base.listener.SnackbarBarListener
import app.trian.filebox.base.listener.TopAppBarListener

class EventListener {
    private var appListener: AppStateListener? = null
    private var bottomBarListener: BottomBarListener? = null
    private var topAppBarListener: TopAppBarListener? = null
    private var snackbarBarListener: SnackbarBarListener? = null

    fun addAppEventListener(listener: AppStateListener) {
        appListener = listener
    }

    fun sendAppEvent(tag: ActionAppState, data: Map<String, String>) {
        appListener?.onMessage(tag, data)
    }

    fun addTopAppBarEventListener(listener: TopAppBarListener) {
        topAppBarListener = listener
    }

    fun send(tag: ActionTopAppBar) {
        topAppBarListener?.onAction(tag)
    }

    fun addBottomBarEventListener(listener: BottomBarListener) {
        bottomBarListener = listener
    }

    fun send(tag: ActionBottomBar) {
        bottomBarListener?.onItemClicked(tag)
    }

    fun addSnackbarEventListener(listener: SnackbarBarListener) {
        snackbarBarListener = listener
    }

    fun send(tag: ActionSnackBar) {
        snackbarBarListener?.onAction(tag)
    }
}