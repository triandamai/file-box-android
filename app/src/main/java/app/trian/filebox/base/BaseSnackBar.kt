package app.trian.filebox.base

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import app.trian.filebox.components.snackbar.SnackBarPickFile

@Composable
fun BaseSnackBar(
    appState: FileBoxState,
    router: NavHostController,
    data: SnackbarData
) {
    with(appState) {
        when (snackBarType) {
            SnackBarType.BASIC -> {
                Snackbar(snackbarData = data)
            }
            SnackBarType.PICK_FILE -> {
                SnackBarPickFile(
                    message = "${appState.selectedFileCount} Selected",
                    onActionClick = {
                        appState.onSnackbarActionClick(TAG_ACTION_SEND)
                    },
                    onMoreClick = {
                        appState.onSnackbarActionClick(TAG_ACTION_MORE)
                    }
                )
            }
        }
    }

}
