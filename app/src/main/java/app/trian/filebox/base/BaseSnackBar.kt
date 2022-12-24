/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.39
 */
package app.trian.filebox.base

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import app.trian.filebox.base.listener.ActionSnackBar
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
                    message = "$selectedFileCount Selected",
                    onActionClick = {
                        onSnackbarActionClick(ActionSnackBar.ACTION_SEND_FILES)
                    },
                    onMoreClick = {
                        onSnackbarActionClick(ActionSnackBar.ACTION_MORE_OPTION)
                    }
                )
            }
        }
    }

}
