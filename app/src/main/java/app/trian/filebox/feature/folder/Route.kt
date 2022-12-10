package app.trian.filebox.feature.folder

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.FileBoxState
import app.trian.filebox.feature.home.Home
import app.trian.filebox.feature.home.HomeViewModel


object Folder {
    const val routeName = "folder"
}


fun NavGraphBuilder.routeFolder(
    router: NavHostController,
    appState: FileBoxState
) {
    composable(Folder.routeName) {
        val viewModel = hiltViewModel<HomeViewModel>()
        ScreenFolder()
    }
}