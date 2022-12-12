package app.trian.filebox.feature.profile

import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.FileBoxState
import app.trian.filebox.composables.BottomBarListener
import app.trian.filebox.feature.home.Home
import app.trian.filebox.feature.home.HomeViewModel
import app.trian.filebox.feature.profile.ProfileViewModel
import app.trian.filebox.feature.profile.ScreenProfile
import kotlinx.coroutines.launch


object Profile {
    const val routeName = "profile"
}


fun NavGraphBuilder.routeProfile(
    router: NavHostController,
    appState: FileBoxState
) {
    composable(Profile.routeName) {
        val viewModel = hiltViewModel<ProfileViewModel>()
        val scope = rememberCoroutineScope()
        ScreenProfile()
    }
}