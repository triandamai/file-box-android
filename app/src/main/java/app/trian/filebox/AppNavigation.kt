package app.trian.filebox

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import app.trian.filebox.feature.folder.routeFolder
import app.trian.filebox.feature.home.routeHome
import app.trian.filebox.feature.profile.routeProfile
import app.trian.filebox.feature.signin.routeSignIn
import app.trian.filebox.feature.signup.routeSignUp

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
            appState=appState
        )
        routeFolder(
            router= navController,
            appState = appState
        )
        routeProfile(
            router = navController,
            appState=appState
        )
    }
}

enum class BottomBarType {
    BLANK,
    RAIL,
    BASIC
}

class FileBoxState internal constructor() {
    var showAppbar by mutableStateOf(false)
    var bottomNavType by mutableStateOf(BottomBarType.BLANK)
    var currentBottomNav by mutableStateOf("")

    fun hideAppbar() {
        if(showAppbar) {
            showAppbar = false
        }
    }

    fun showAppbar() {
        if(!showAppbar) {
            showAppbar = true
        }
    }

    fun hideBottomNav() {
        if(bottomNavType != BottomBarType.BLANK) {
            bottomNavType = BottomBarType.BLANK
        }
    }

    fun showBottomNav(type: BottomBarType) {
        if(bottomNavType != type) {
            bottomNavType = type
        }
    }

    fun setActiveBottomNav(route:String){
        if(currentBottomNav != route) {
            currentBottomNav = route
        }
    }
}

@Composable
fun rememberFileBoxApplication(): FileBoxState {
    val state = remember {
        FileBoxState()
    }

    return state
}