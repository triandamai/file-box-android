package app.trian.filebox.feature.detailFolder

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import app.trian.filebox.FileBoxState
import app.trian.filebox.feature.home.ScreenHome


object DetailFolder {
    private const val routeName = "detail_folder"
    const val argKey = "folderId"

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

    fun NavGraphBuilder.routeDetailFolder(
        router: NavHostController,
        appState: FileBoxState
    ) {
        composable(
            routeName(),
            arguments = getArg()
        ) {
            val viewModel = hiltViewModel<DetailFolderViewModel>()
            ScreenHome()
        }
    }

}


