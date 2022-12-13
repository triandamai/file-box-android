package app.trian.filebox.feature.home

import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.FileBoxState


object Home {
    const val routeName = "home"
}


fun NavGraphBuilder.routeHome(
    router: NavHostController,
    appState: FileBoxState
) {
    composable(
        Home.routeName,
    ) {
        val viewModel = hiltViewModel<HomeViewModel>()
        val scope = rememberCoroutineScope()

        ScreenHome()
    }
}