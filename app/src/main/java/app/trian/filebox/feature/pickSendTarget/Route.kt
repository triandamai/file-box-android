package app.trian.filebox.feature.pickSendTarget

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.base.FileBoxState
import app.trian.filebox.feature.blank.Blank
import app.trian.filebox.feature.blank.BlankViewModel


object PickSendTarget {
    const val routeName = "send_target"


}

fun NavGraphBuilder.routeSendTarget(
    router: NavHostController,
    appState: FileBoxState
) {
    composable(PickSendTarget.routeName) {
        val viewModel = hiltViewModel<SendTargetViewModel>()
        ScreenSendTarget()
    }
}
