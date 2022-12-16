package app.trian.filebox.feature.homeSend

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.FileBoxState
import app.trian.filebox.data.models.FileModel


object HomeSend {
    const val routeName = "home_send"
}

fun NavGraphBuilder.routeHomeSend(
    router: NavHostController,
    appState: FileBoxState
) {
    composable(HomeSend.routeName) {
        val viewModel = hiltViewModel<HomeSendViewModel>()
        val allFiles = mapOf<String, List<FileModel>>()
        ScreenHomeSend(
            allFiles = mapOf("2 December 2022" to listOf(
                FileModel(
                    name = "Imagewaha.jpg"
                )
            ))
        )
    }
}
