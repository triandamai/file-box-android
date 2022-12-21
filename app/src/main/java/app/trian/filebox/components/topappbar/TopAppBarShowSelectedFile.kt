package app.trian.filebox.components.topappbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.filebox.base.BaseContainer
import app.trian.filebox.base.FileBoxState
import app.trian.filebox.base.rememberFileBoxApplication

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarShowSelectedFile(
    appState:FileBoxState = rememberFileBoxApplication(),
    router:NavHostController = rememberNavController()
) {
    TopAppBar(
        actions = {
            TextButton(onClick = {
                appState.onTopAppBarItemClick()
            }) {
                Text(text = "Send")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Outlined.Link, contentDescription = "")
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                router.popBackStack()
            }) {
                Icon(imageVector = Icons.Outlined.Close, contentDescription = "")
            }
        },
        title = {
            Text(text = "1 Selected")
        }
    )
}

@Preview
@Composable
fun PreviewAppbarShowSelectedFile() {
    BaseContainer(
        topBar = {
            TopAppBarShowSelectedFile()
        }
    )
}