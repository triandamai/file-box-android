package app.trian.filebox.feature.homeSend

import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.FileBoxState
import app.trian.filebox.feature.blank.BlankViewModel
import app.trian.filebox.feature.home.ScreenHome
import app.trian.filebox.feature.signin.SignIn


object HomeSend {
    const val routeName = "home_send"

    fun NavGraphBuilder.routeHomeSend(
        router: NavHostController,
        appState: FileBoxState
    ) {
        composable(routeName) {
            val viewModel = hiltViewModel<HomeSendViewModel>()
            val allFiles by viewModel.allFiles.observeAsState(
                initial = mapOf()
            )
            ScreenHomeSend(
                allFiles = allFiles
            )
        }
    }
}


