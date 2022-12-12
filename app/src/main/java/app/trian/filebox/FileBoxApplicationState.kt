package app.trian.filebox

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

enum class BottomBarType {
    BLANK,
    BASIC
}

class FileBoxState internal constructor() {
    var showAppbar by mutableStateOf(false)
    var bottomBarType by mutableStateOf(BottomBarType.BLANK)
    var showNavigationRail by mutableStateOf(false)
    var activeRoute by mutableStateOf("")
    var snackbarHostState by mutableStateOf(SnackbarHostState())



    fun hideAppbar() {
        if (showAppbar) {
            showAppbar = false
        }
    }

    fun showAppbar() {
        if (!showAppbar) {
            showAppbar = true
        }
    }

    fun showNavRail() {
        if (!showNavigationRail) {
            showNavigationRail = true
        }
    }

    fun hideNavRail() {
        if (showNavigationRail) {
            showNavigationRail = false
        }
    }

    fun hideBottomBar() {
        if (bottomBarType != BottomBarType.BLANK) {
            bottomBarType = BottomBarType.BLANK
        }
    }

    fun changeBottomBar(type: BottomBarType) {
        if (bottomBarType != type) {
            bottomBarType = type
        }
    }

    fun setCurrentRoute(route: String) {
        if (activeRoute != route) {
            activeRoute = route
        }
    }
}

@Composable
fun rememberFileBoxApplication(): FileBoxState {
    val state = remember {
        FileBoxState()
    }

    return state
}