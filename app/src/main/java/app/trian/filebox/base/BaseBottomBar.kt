package app.trian.filebox.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.filebox.components.bottombar.FileBoxBottomNavigation

@Composable
fun BaseBottomBar(
    appState: FileBoxState,
    router: NavHostController
) {
    with(appState) {
        when (bottomBarType) {
            BottomBarType.BASIC -> {

                FileBoxBottomNavigation(
                    items = listOf(
                        FileBoxBottomNavigation.Send(),
                        FileBoxBottomNavigation.Receive(),
                        FileBoxBottomNavigation.History()
                    ),
                    currentRoute = activeRoute,
                    onItemClick = {
                        router.navigate(it) {
                            launchSingleTop = true
                        }
                        setCurrentRoute(it)
                    }
                )
            }
            else -> {}
        }
    }

}


@Preview
@Composable
fun PreviewBottomBar() {
    BaseContainer(
        bottomBar = {
            BaseBottomBar(
                appState = rememberFileBoxApplication(), router = rememberNavController()
            )
        }
    ) {}
}