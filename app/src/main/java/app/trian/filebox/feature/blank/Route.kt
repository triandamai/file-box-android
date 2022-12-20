package app.trian.filebox.feature.blank

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.base.FileBoxState


object Blank {
    const val routeName = "sign_in"


}

fun NavGraphBuilder.routeBlank(
    router: NavHostController,
    appState: FileBoxState
) {
    composable(Blank.routeName) {
        val viewModel = hiltViewModel<BlankViewModel>()
        ScreenBlank()
    }
}
