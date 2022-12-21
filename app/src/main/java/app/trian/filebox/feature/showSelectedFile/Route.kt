package app.trian.filebox.feature.showSelectedFile

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.base.FileBoxState


object ShowSelectedFile {
    const val routeName = "show_selected_file"


}

fun NavGraphBuilder.routeShowSelectedFile(
    router: NavHostController,
    appState: FileBoxState
) {
    composable(ShowSelectedFile.routeName) {
        val viewModel = hiltViewModel<ShowSelectedFileViewModel>()

        ScreenShowSelectedFile(

        )
    }
}
