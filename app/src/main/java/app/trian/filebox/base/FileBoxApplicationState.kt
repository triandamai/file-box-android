package app.trian.filebox.base

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

enum class BottomBarType {
    HIDE,
    BASIC,
    PICK_FILE,
}

enum class TopAppBarType {
    HIDE,
    BASIC,
}

class FileBoxState internal constructor() {
    var topAppBarType by mutableStateOf(TopAppBarType.HIDE)
    var bottomBarType by mutableStateOf(BottomBarType.HIDE)
    var showNavigationRail by mutableStateOf(false)
    var activeRoute by mutableStateOf("")
    var snackbarHostState by mutableStateOf(SnackbarHostState())
    var selectedFileCount by mutableStateOf(0)

    private var onBottomBarListener: BottomBarListener? = null
    private var onTopAppBarListener: TopAppBarListener? = null


    fun hideAppbar() {
        if (topAppBarType != TopAppBarType.HIDE) {
            topAppBarType = TopAppBarType.HIDE
        }
    }

    fun showAppbar(appBarType: TopAppBarType) {
        if (topAppBarType != appBarType) {
            topAppBarType = appBarType
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
        if (bottomBarType != BottomBarType.HIDE) {
            bottomBarType = BottomBarType.HIDE
        }
    }

    fun showBottomBar(type: BottomBarType) {

        if (bottomBarType != type) {
            bottomBarType = type
        }
    }

    fun setCurrentRoute(route: String) {
        if (activeRoute != route) {
            activeRoute = route
        }
    }

    fun addBottomBarListener(listener: BottomBarListener) {
        onBottomBarListener = listener
    }

    fun addTopAppBarListener(listener: TopAppBarListener) {
        onTopAppBarListener = listener
    }

    fun onBottomBarItemClick(tag: String = "", data: Map<String, String> = mapOf()) {
        onBottomBarListener?.onItemClicked(tag, data)
    }

    fun onTopAppBarItemClick(tag: String = "", data: Map<String, String> = mapOf()) {
        onTopAppBarListener?.onItemClicked(tag, data)
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