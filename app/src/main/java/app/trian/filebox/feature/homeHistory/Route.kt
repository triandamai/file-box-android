package app.trian.filebox.feature.homeHistory

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.FileBoxState
import app.trian.filebox.data.models.FileModel
import app.trian.filebox.feature.homeSend.HomeSendViewModel


object HomeHistory {
    const val routeName = "home_history"
}

fun NavGraphBuilder.routeHomeHistory(
    router: NavHostController,
    appState: FileBoxState
) {
    composable(HomeHistory.routeName) {
        val viewModel = hiltViewModel<HomeSendViewModel>()
        val allFiles = mapOf<String, List<FileModel>>()
        ScreenHomeHistory(
            allFiles = allFiles
        )
    }
}
