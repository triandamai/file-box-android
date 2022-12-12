package app.trian.filebox.feature.folder

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.FileBoxState
import app.trian.filebox.composables.BottomBarListener
import app.trian.filebox.feature.home.Home
import app.trian.filebox.feature.home.HomeViewModel
import kotlinx.coroutines.launch


object Folder {
    const val routeName = "folder"
}


fun NavGraphBuilder.routeFolder(
    router: NavHostController,
    appState: FileBoxState
) {
    composable(Folder.routeName) {
        val viewModel = hiltViewModel<HomeViewModel>()
        val scope = rememberCoroutineScope()
        appState.addBottomBarListener(object : BottomBarListener<String> {
            override fun onItemClicked(data: String) {

            }

        })
        ScreenFolder()
    }
}