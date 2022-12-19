package app.trian.filebox.feature.homeSend

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.FileBoxState


object HomeSend {
    const val routeName = "home_send"
}

fun NavGraphBuilder.routeHomeSend(
    router: NavHostController,
    appState: FileBoxState
) {
    composable(HomeSend.routeName) {
        val viewModel = hiltViewModel<HomeSendViewModel>()
        val images by viewModel.images.collectAsState()
        ScreenHomeSend(
            images = images
        )
    }
}
