package app.trian.filebox

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import app.trian.filebox.base.FileBoxState
import app.trian.filebox.feature.homeHistory.routeHomeHistory
import app.trian.filebox.feature.homeReceive.routeHomeReceive
import app.trian.filebox.feature.homeSend.routeHomeSend
import app.trian.filebox.feature.sendFileOverview.routeSendFileOverview
import app.trian.filebox.feature.showSelectedFile.routeShowSelectedFile
import app.trian.filebox.feature.signin.routeSignIn
import app.trian.filebox.feature.signup.routeSignUp

@Composable
fun AppNavigation(
    navController: NavHostController, appState: FileBoxState, startDestination: String = ""
) {
    NavHost(
        navController = navController, startDestination = startDestination
    ) {
        routeSignIn(
            router = navController,
            appState = appState
        )
        routeSignUp(
            router = navController,
            appState = appState
        )
        routeHomeSend(
            router = navController,
            appState = appState
        )
        routeHomeReceive(
            router = navController,
            appState = appState
        )
        routeHomeHistory(
            router = navController,
            appState = appState
        )
        routeShowSelectedFile(
            router = navController,
            appState = appState
        )
        routeSendFileOverview(
            router = navController,
            appState = appState
        )
    }
}
