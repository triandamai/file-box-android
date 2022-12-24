/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.38
 */
package app.trian.filebox.base.extensions

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarResult
import app.trian.filebox.base.BottomBarType
import app.trian.filebox.base.FileBoxState
import app.trian.filebox.base.SnackBarType
import app.trian.filebox.base.TopAppBarType

fun FileBoxState.updateSelectedFileCount(currentSize: Int = 0) {
    with(this) {
        selectedFileCount = currentSize
    }
}

fun FileBoxState.hideAppbar() {
    if (topAppBarType != TopAppBarType.HIDE) {
        topAppBarType = TopAppBarType.HIDE
    }
}

fun FileBoxState.showAppbar(appBarType: TopAppBarType) {
    if (topAppBarType != appBarType) {
        topAppBarType = appBarType
    }
}

fun FileBoxState.showNavRail() {
    if (!showNavigationRail) {
        showNavigationRail = true
    }
}

fun FileBoxState.hideNavRail() {
    if (showNavigationRail) {
        showNavigationRail = false
    }
}

fun FileBoxState.hideBottomBar() {
    if (bottomBarType != BottomBarType.HIDE) {
        bottomBarType = BottomBarType.HIDE
    }
}

fun FileBoxState.showBottomBar(type: BottomBarType) {

    if (bottomBarType != type) {
        bottomBarType = type
    }
}


fun FileBoxState.setCurrentRoute(route: String) {
    if (activeRoute != route) {
        activeRoute = route
    }
}


suspend fun FileBoxState.showSnackbar(message: String): SnackbarResult = with(snackbarHostState) {
    if (snackBarType != SnackBarType.BASIC) {
        snackBarType = SnackBarType.BASIC
    }
    showSnackbar(message)
}

suspend fun FileBoxState.showSnackbar(message: String, type: SnackBarType): SnackbarResult =
    with(snackbarHostState) {
        if (snackBarType != type) {
            snackBarType = type
        }
        return if (currentSnackbarData == null) {
            showSnackbar(message, duration = SnackbarDuration.Indefinite)
        } else SnackbarResult.Dismissed
    }

fun FileBoxState.hideSnackbar() = with(snackbarHostState) {
    currentSnackbarData?.dismiss()
}

suspend fun FileBoxState.showSnackbar(
    message: String,
    actionLabel: String? = null,
    withDismissAction: Boolean = false,
    duration: SnackbarDuration =
        if (actionLabel == null) SnackbarDuration.Short else SnackbarDuration.Indefinite
) = with(snackbarHostState) {
    if (snackBarType != SnackBarType.BASIC) {
        snackBarType = SnackBarType.BASIC
    }
    showSnackbar(
        message = message,
        actionLabel = actionLabel,
        withDismissAction = withDismissAction,
        duration = duration
    )
}