package app.trian.filebox.base

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTopAppBar(
    appState: FileBoxState,
    router: NavHostController
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
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun PreviewBBaseTopAppBar() {
    BaseContainer(
        bottomBar = {
            BaseTopAppBar(
                appState = rememberFileBoxApplication(), router = rememberNavController()
            )
        }
    ) {}
}