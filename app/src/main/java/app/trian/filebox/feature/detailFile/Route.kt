package app.trian.filebox.feature.detailFile

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import app.trian.filebox.FileBoxState
import app.trian.filebox.feature.detailFolder.DetailFolder
import app.trian.filebox.feature.home.ScreenHome


object DetailFile {
    const val routeName = "detailFile"
    const val argKey = "fileId"

    fun routeName() = "$routeName/{$argKey}"

    fun getArg() = listOf(
        navArgument(
            name = argKey,
            builder = {
                NavType.StringType
            }
        )
    )

    fun getRouteName(folderId: String) = routeName
        .plus("/")
        .plus(folderId)
}


fun NavGraphBuilder.routeDetailFile(
    router: NavHostController,
    appState: FileBoxState
) {
    composable(
        DetailFile.routeName(),
        arguments = DetailFolder.getArg()
    ) {
        val viewModel = hiltViewModel<DetailFileViewModel>()
        ScreenHome()
    }
}