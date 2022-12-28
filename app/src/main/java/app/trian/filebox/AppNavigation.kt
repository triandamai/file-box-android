/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.49
 */
package app.trian.filebox

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import app.trian.filebox.base.EventListener
import app.trian.filebox.base.FileBoxState
import app.trian.filebox.feature.homeHistory.routeHomeHistory
import app.trian.filebox.feature.homeReceive.routeHomeReceive
import app.trian.filebox.feature.homeSend.routeHomeSend
import app.trian.filebox.feature.sendFileOverview.routeSendFileOverview
import app.trian.filebox.feature.showSelectedFile.routeShowSelectedFile
import app.trian.filebox.feature.signin.routeSignIn
import app.trian.filebox.feature.signup.routeSignUp
import app.trian.filebox.feature.splash.routeSplash

@Composable
fun AppNavigation(
    navController: NavHostController,
    appState: FileBoxState,
    startDestination: String = "",
    event: EventListener= EventListener()
) {
    NavHost(
        navController = navController, startDestination = startDestination
    ) {
        routeSplash(
            navController
        )
        routeSignIn(
            router = navController,
            appState = appState,
            event=event
        )
        routeSignUp(
            router = navController,
            appState = appState,
            event=event
        )
        routeHomeSend(
            router = navController,
            appState = appState,
            event=event
        )
        routeHomeReceive(
            router = navController,
            appState = appState,
            event=event
        )
        routeHomeHistory(
            router = navController,
            appState = appState,
            event=event
        )
        routeShowSelectedFile(
            router = navController,
            appState = appState,
            event=event
        )
        routeSendFileOverview(
            router = navController,
            appState = appState,
            event=event
        )
    }
}
