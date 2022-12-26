/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.39
 */
package app.trian.filebox.base

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.filebox.feature.showSelectedFile.components.TopAppBarShowSelectedFile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTopAppBar(
    appState: FileBoxState,
    router: NavHostController,
    event: EventListener = EventListener()
) {
    when (appState.topAppBarType) {
        TopAppBarType.HIDE -> {}
        TopAppBarType.BASIC -> {
            TopAppBar(
                title = {
                    Text(text = "File Box")
                }
            )
        }
        TopAppBarType.SHOW_SELECTED_FILE -> {
            TopAppBarShowSelectedFile(
                appState=appState,
                router=router,
                event=event
            )
        }
    }

}

@Preview
@Composable
fun PreviewBBaseTopAppBar() {
    BaseContainer(
        bottomBar = {
            BaseTopAppBar(
                appState = rememberFileBoxApplication(),
                router = rememberNavController(),

            )
        }
    ) {}
}