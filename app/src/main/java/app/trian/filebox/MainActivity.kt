package app.trian.filebox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import app.trian.filebox.components.FileBoxBottomNavigation
import app.trian.filebox.feature.folder.Folder
import app.trian.filebox.feature.home.Home
import app.trian.filebox.feature.profile.Profile
import app.trian.filebox.feature.signin.SignIn
import app.trian.filebox.feature.signup.SignUp
import app.trian.filebox.ui.theme.FileBoxTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val router = rememberNavController()
            val appState = rememberFileBoxApplication()

            router.addOnDestinationChangedListener { controller, destination, arguments ->
                with(appState) {
                    setActiveBottomNav(destination.route.orEmpty())
                    when (destination.route) {
                        in listOf(
                            Home.routeName,
                            Folder.routeName,
                            Profile.routeName
                        ) -> {
                            showBottomNav(BottomBarType.BASIC)
                            showAppbar()
                        }
                        in listOf(
                            SignIn.routeName,
                            SignUp.routeName
                        ) -> {
                            hideBottomNav()
                            hideAppbar()
                        }
                        else -> {
                            hideBottomNav()
                            hideAppbar()
                        }
                    }
                }

            }

            FileBoxTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
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
                            when (appState.bottomNavType) {
                                BottomBarType.BLANK -> Unit
                                BottomBarType.RAIL -> {}
                                BottomBarType.BASIC -> {
                                    FileBoxBottomNavigation(
                                        items = listOf(
                                            FileBoxBottomNavigation.Home(),
                                            FileBoxBottomNavigation.Folder(),
                                            FileBoxBottomNavigation.Profile()
                                        ),
                                        currentRoute = appState.currentBottomNav,
                                        onItemClick = {
                                            router.navigate(it) {
                                                launchSingleTop = true
                                            }
                                            appState.setActiveBottomNav(it)
                                        }
                                    )
                                }
                            }
                        },
                    ) {
                        AppNavigation(
                            padding = it,
                            navController = router,
                            appState = appState,
                            startDestination = SignIn.routeName,
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseContainer(
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    FileBoxTheme {

        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            Scaffold(
                contentColor = MaterialTheme.colorScheme.background,
                containerColor = MaterialTheme.colorScheme.background,
                topBar = topBar,
                bottomBar = bottomBar,
                snackbarHost = snackbarHost
            ) {
                it.calculateBottomPadding()
                content.invoke()
            }
        }
    }
}