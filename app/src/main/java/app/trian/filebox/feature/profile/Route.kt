package app.trian.filebox.feature.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.FileBoxState


object Profile {
    const val routeName = "profile"

    fun NavGraphBuilder.routeProfile(
        router: NavHostController,
        appState: FileBoxState
    ) {
        composable(routeName) {
//        val viewModel = hiltViewModel<ProfileViewModel>()
//        val scope = rememberCoroutineScope()
            ScreenProfile()
        }
    }
}


