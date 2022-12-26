/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.39
 */
package app.trian.filebox.base

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import app.trian.filebox.base.listener.ActionSnackBar
import app.trian.filebox.components.snackbar.SnackBarPickFile

@Composable
fun BaseSnackBar(
    appState: FileBoxState,
    router: NavHostController,
    event:EventListener=EventListener()
) {
    SnackbarHost(
        hostState = appState.snackbarHostState,
        snackbar = {
            with(appState) {
                when (snackBarType) {
                    SnackBarType.BASIC -> {
                        Snackbar(
                            snackbarData = it,
                            contentColor = MaterialTheme.colorScheme.onSurface,
                            containerColor = MaterialTheme.colorScheme.surface,
                            actionColor = MaterialTheme.colorScheme.primary,
                            actionContentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    SnackBarType.PICK_FILE -> {
                        SnackBarPickFile(
                            message = "$selectedFileCount Selected",
                            onActionClick = {
                                event.send(ActionSnackBar.ACTION_SEND_FILES)
                            },
                            onMoreClick = {
                                event.send(ActionSnackBar.ACTION_MORE_OPTION)
                            }
                        )
                    }
                }
            }
        }
    )


}
