package app.trian.filebox.feature.folder

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.FileBoxState
import app.trian.filebox.composables.BottomBarListener
import app.trian.filebox.feature.detailFolder.DetailFolder
import app.trian.filebox.feature.home.HomeViewModel
import kotlinx.coroutines.launch


object Folder {
    const val routeName = "folder"
}

fun NavHostController.navigateToDetailFolder(folderId:String)= with(this){
    navigate(DetailFolder.getRouteName(folderId)){
        launchSingleTop = true
    }
}

fun NavGraphBuilder.routeFolder(
    router: NavHostController,
    appState: FileBoxState
) {
    composable(Folder.routeName) {
        val viewModel = hiltViewModel<HomeViewModel>()
        val scope = rememberCoroutineScope()
        LaunchedEffect(key1 = appState.snackbarHostState, block = {
            with(appState) {
                addBottomBarListener(object : BottomBarListener<String> {
                    override fun onItemClicked(data: String) {
                        scope.launch {
                            snackbarHostState.showSnackbar(data)
                        }
                    }

                })
            }
        })
        ScreenFolder()
    }
}