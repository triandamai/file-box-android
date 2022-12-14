package app.trian.filebox.feature.blank

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.FileBoxState
import app.trian.filebox.feature.home.ScreenHome


object Blank {
    const val routeName = "sign_in"

    fun NavGraphBuilder.routeBlank(
        router: NavHostController,
        appState: FileBoxState
    ) {
        composable(routeName) {
            val viewModel = hiltViewModel<BlankViewModel>()
            ScreenHome()
        }
    }
}


