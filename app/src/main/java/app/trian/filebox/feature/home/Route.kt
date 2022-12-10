package app.trian.filebox.feature.home

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.FileBoxState
import app.trian.filebox.feature.signin.SignIn


object Home {
    const val routeName = "home"
}


fun NavGraphBuilder.routeHome(
    router: NavHostController,
    appState: FileBoxState
) {
    composable(Home.routeName) {
        val viewModel = hiltViewModel<HomeViewModel>()
        ScreenHome()
    }
}