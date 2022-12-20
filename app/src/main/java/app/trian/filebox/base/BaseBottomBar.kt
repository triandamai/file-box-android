package app.trian.filebox.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.filebox.components.BottomBarSelectFile
import app.trian.filebox.components.FileBoxBottomNavigation

@Composable
fun BaseBottomBar(
    appState: FileBoxState,
    router: NavHostController
) {
    when (appState.bottomBarType) {
        BottomBarType.BASIC -> {

            FileBoxBottomNavigation(
                items = listOf(
                    FileBoxBottomNavigation.Send(),
                    FileBoxBottomNavigation.Receive(),
                    FileBoxBottomNavigation.History()
                ),
                currentRoute = appState.activeRoute,
                onItemClick = {
                    router.navigate(it) {
                        launchSingleTop = true
                    }
                    appState.setCurrentRoute(it)
                }
            )
        }
        BottomBarType.PICK_FILE -> {

            BottomBarSelectFile(
                message = "${appState.selectedFileCount} File Selected",
                onDetailClicked = {},
                onShareClicked = {}
            )
        }
        else -> {}
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