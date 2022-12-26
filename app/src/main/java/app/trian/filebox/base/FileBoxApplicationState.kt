/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.39
 */
package app.trian.filebox.base

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.trian.filebox.base.listener.ActionAppState
import app.trian.filebox.base.listener.ActionSnackBar
import app.trian.filebox.base.listener.ActionTopAppBar
import app.trian.filebox.base.listener.ActionBottomBar
import app.trian.filebox.base.listener.AppStateListener
import app.trian.filebox.base.listener.BottomBarListener
import app.trian.filebox.base.listener.SnackbarBarListener
import app.trian.filebox.base.listener.TopAppBarListener

enum class BottomBarType {
    HIDE,
    BASIC,
}

enum class TopAppBarType {
    HIDE,
    BASIC,
    SHOW_SELECTED_FILE
}

enum class SnackBarType {
    BASIC,
    PICK_FILE
}


class FileBoxState internal constructor() {
    var topAppBarType by mutableStateOf(TopAppBarType.HIDE)
    var bottomBarType by mutableStateOf(BottomBarType.HIDE)
    var snackBarType by mutableStateOf(SnackBarType.BASIC)

    var showNavigationRail by mutableStateOf(false)
    var activeRoute by mutableStateOf("")
    var snackbarHostState by mutableStateOf(SnackbarHostState())
    var selectedFileCount by mutableStateOf(0)

}

@Composable
fun rememberFileBoxApplication(): FileBoxState {
    return remember {
        FileBoxState()
    }
}