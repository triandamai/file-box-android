package app.trian.filebox.feature.showSelectedFile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.filebox.base.BaseContainer
import app.trian.filebox.base.FileBoxState
import app.trian.filebox.base.listener.ActionAppState
import app.trian.filebox.base.rememberFileBoxApplication

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarShowSelectedFile(
    appState: FileBoxState = rememberFileBoxApplication(),
    router: NavHostController = rememberNavController()
) {
    var showSelection by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = appState, block = {
        with(appState) {
            addOnMessageListener { tag, _ ->
                when (tag) {
                    ActionAppState.NOTHING -> Unit
                    ActionAppState.SHOW_SELECTION -> {
                        showSelection = true
                    }
                    ActionAppState.HIDE_SELECTION -> {
                        showSelection = false
                    }
                }
            }
        }
    })
    TopAppBar(
        actions = {
            TextButton(onClick = {
                appState.onTopAppBarItemClick()
            }) {
                Text(
                    text = "Send"
                )
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (showSelection) {
                    Checkbox(
                        checked = false,
                        onCheckedChange = {},
                        colors = CheckboxDefaults.colors(
                            checkedColor = MaterialTheme.colorScheme.secondary,
                            uncheckedColor = MaterialTheme.colorScheme.onBackground
                        )
                    )
                }
                Text(text = "1 Selected")
            }
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