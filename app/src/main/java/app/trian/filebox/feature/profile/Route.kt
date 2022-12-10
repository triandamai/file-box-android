package app.trian.filebox.feature.profile

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.FileBoxState
import app.trian.filebox.feature.home.Home
import app.trian.filebox.feature.home.HomeViewModel
import app.trian.filebox.feature.profile.ProfileViewModel
import app.trian.filebox.feature.profile.ScreenProfile


object Profile {
    const val routeName = "profile"
}


fun NavGraphBuilder.routeProfile(
    router: NavHostController,
    appState: FileBoxState
) {
    composable(Profile.routeName) {
        val viewModel = hiltViewModel<ProfileViewModel>()
        ScreenProfile()
    }
}