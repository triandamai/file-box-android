package app.trian.filebox

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.trian.filebox.composables.BottomBarListener

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

    private var onBottomBarListener: BottomBarListener<String>? = null


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

    fun addBottomBarListener(listener: BottomBarListener<String>) {
        onBottomBarListener = listener
    }

    fun onBottomBarClick(data: String) {
        onBottomBarListener?.onItemClicked(data)
    }

    suspend fun showSnackbar(message: String): SnackbarResult = with(snackbarHostState) {
        showSnackbar(message)
    }

    suspend fun showSnackbar(
        message: String,
        actionLabel: String? = null,
        withDismissAction: Boolean = false,
        duration: SnackbarDuration =
            if (actionLabel == null) SnackbarDuration.Short else SnackbarDuration.Indefinite
    ) = with(snackbarHostState) {
        showSnackbar(
            message = message,
            actionLabel = actionLabel,
            withDismissAction = withDismissAction,
            duration = duration
        )
    }

}

@Composable
fun rememberFileBoxApplication(): FileBoxState {
    val state = remember {
        FileBoxState()
    }

    return state
}