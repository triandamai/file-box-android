package app.trian.filebox.feature.homeReceive

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.FileBoxState
import app.trian.filebox.data.models.FileModel
import app.trian.filebox.feature.homeSend.HomeSendViewModel


object HomeReceive {
    const val routeName = "home_receive"
}

fun NavGraphBuilder.routeHomeReceive(
    router: NavHostController,
    appState: FileBoxState
) {
    composable(HomeReceive.routeName) {
        val viewModel = hiltViewModel<HomeSendViewModel>()
        val allFiles = mapOf<String, List<FileModel>>()
        ScreenHomeReceive(
            allFiles = allFiles
        )
    }
}
