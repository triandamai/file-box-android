package app.trian.filebox

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import app.trian.filebox.feature.folder.Folder.routeFolder
import app.trian.filebox.feature.home.Home.routeHome
import app.trian.filebox.feature.homeSend.HomeSend.routeHomeSend
import app.trian.filebox.feature.profile.Profile.routeProfile
import app.trian.filebox.feature.signin.SignIn.routeSignIn
import app.trian.filebox.feature.signup.SignUp.routeSignUp

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    appState: FileBoxState = rememberFileBoxApplication(),
    startDestination: String = ""
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        routeSignIn(
            router = navController,
            appState = appState
        )
        routeSignUp(
            router = navController,
            appState = appState
        )
        routeHome(
            router = navController,
            appState = appState
        )
        routeFolder(
            router = navController,
            appState = appState
        )
        routeProfile(
            router = navController,
            appState = appState
        )
        routeHomeSend(
            router = navController,
            appState = appState
        )
    }
}
