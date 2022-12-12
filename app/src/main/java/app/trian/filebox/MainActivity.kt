package app.trian.filebox

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.compose.rememberNavController
import app.trian.filebox.components.FileBoxBottomNavigation
import app.trian.filebox.feature.folder.Folder
import app.trian.filebox.feature.home.Home
import app.trian.filebox.feature.profile.Profile
import app.trian.filebox.feature.signin.SignIn
import app.trian.filebox.feature.signup.SignUp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val router = rememberNavController()
            val appState = rememberFileBoxApplication()
            val config = LocalConfiguration.current
            val scope = rememberCoroutineScope()



            router.addOnDestinationChangedListener { _, destination, _ ->
                with(appState) {
                    setCurrentRoute(destination.route.orEmpty())
                    when (destination.route) {
                        in listOf(
                            Home.routeName,
                            Folder.routeName,
                            Profile.routeName
                        ) -> {
                            if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                                showNavRail()
                                hideBottomBar()
                            } else {
                                hideNavRail()
                                changeBottomBar(BottomBarType.BASIC)
                            }
                            showAppbar()
                        }
                        in listOf(
                            SignIn.routeName,
                            SignUp.routeName
                        ) -> {
                            hideNavRail()
                            hideBottomBar()
                            hideAppbar()
                        }
                        else -> {
                            hideNavRail()
                            hideBottomBar()
                            hideAppbar()
                        }
                    }
                }
            }

            BaseContainer(
                topBar = {
                    if (appState.showAppbar) {
                        TopAppBar(
                            title = {
                                Text(text = "File Box")
                            }
                        )
                    }
                },
                bottomBar = {
                    when (appState.bottomBarType) {
                        BottomBarType.BASIC -> {
                            FileBoxBottomNavigation(
                                items = listOf(
                                    FileBoxBottomNavigation.Home(),
                                    FileBoxBottomNavigation.Folder(),
                                    FileBoxBottomNavigation.Profile()
                                ),
                                currentRoute = appState.activeRoute,
                                onItemClick = {
                                    scope.launch {
                                        appState.onBottomBarClick("Route -> "+it)
                                    }
                                    router.navigate(it) {
                                        launchSingleTop = true
                                    }
                                    appState.setCurrentRoute(it)
                                }
                            )
                        }
                        else -> {}
                    }
                },
                snackbarHost = {
                    SnackbarHost(hostState = appState.snackbarHostState)
                },
                appState = appState,
                router = router
            ) {
                AppNavigation(
                    navController = router,
                    appState = appState,
                    startDestination = SignIn.routeName,
                )
            }
        }
    }
}

