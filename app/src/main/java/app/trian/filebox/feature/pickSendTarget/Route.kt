package app.trian.filebox.feature.pickSendTarget

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.base.FileBoxState
import app.trian.filebox.base.TAG_DELETE_ALL_SELECTED_FILE
import app.trian.filebox.base.updateSelectedFileCount


object PickSendTarget {
    const val routeName = "send_target"


}

fun NavGraphBuilder.routeSendTarget(
    router: NavHostController,
    appState: FileBoxState
) {
    composable(PickSendTarget.routeName) {
        val viewModel = hiltViewModel<SendTargetViewModel>()
        val selected by viewModel.selectedFile.collectAsState(initial = listOf())


        LaunchedEffect(appState, selected) {
            with(appState) {
                addBottomBarListener { tag, _ ->
                    when (tag) {
                        TAG_DELETE_ALL_SELECTED_FILE -> {
                            viewModel.clearSelectedFile()
                            updateSelectedFileCount(
                                currentSize = 0
                            )
                        }

                    }
                }
            }
            if (selected.isNotEmpty()) {
                appState.updateSelectedFileCount(
                    currentSize = selected.size
                )
            }
        }
        ScreenSendTarget()
    }
}
