package app.trian.filebox

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.filebox.components.FileBoxBottomNavigation
import app.trian.filebox.components.FileBoxNavigationRail
import app.trian.filebox.ui.theme.FileBoxTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseContainer(
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    appState: FileBoxState = rememberFileBoxApplication(),
    router: NavHostController = rememberNavController(),
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
                snackbarHost = snackbarHost,
                contentWindowInsets = WindowInsets.navigationBars
            ) {
                with(appState) {
                    if (showNavigationRail) {
                        Row(
                            modifier = Modifier
                                .padding(it)
                                .fillMaxSize()
                        ) {
                            FileBoxNavigationRail(
                                items = listOf(
                                    FileBoxBottomNavigation.Home(),
                                    FileBoxBottomNavigation.Folder(),
                                    FileBoxBottomNavigation.Profile()
                                ),
                                currentRoute = appState.activeRoute,
                                onItemClick = {
                                    router.navigate(it) {
                                        launchSingleTop = true
                                    }
                                    appState.setCurrentRoute(it)
                                }
                            )


                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                content.invoke()
                            }
                        }
                    } else {
                        Column(
                            modifier = Modifier
                                .padding(it)
                                .fillMaxSize()
                        ) {
                            content.invoke()
                        }
                    }
                }
            }
        }
    }
}