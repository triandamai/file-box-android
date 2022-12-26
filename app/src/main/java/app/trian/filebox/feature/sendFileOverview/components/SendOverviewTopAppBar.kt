/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.48
 */
package app.trian.filebox.feature.sendFileOverview.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.filebox.base.BaseContainer
import app.trian.filebox.base.EventListener
import app.trian.filebox.base.FileBoxState
import app.trian.filebox.base.listener.ActionAppState
import app.trian.filebox.base.rememberFileBoxApplication

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendOverviewTopAppBar(
    appState:FileBoxState = rememberFileBoxApplication(),
    router:NavHostController = rememberNavController(),
    event:EventListener=EventListener()
) {
    LaunchedEffect(event){
        with(event){
            addAppEventListener{
                tag,data->
                when(tag){
                    ActionAppState.NOTHING -> Unit
                    ActionAppState.SHOW_SELECTION -> Unit
                    ActionAppState.HIDE_SELECTION -> Unit
                }
            }
        }
    }
    TopAppBar(
        title = {
            Text(text = "Waiting")
        },
        navigationIcon = {
            IconButton(onClick = {  router.popBackStack() }) {
                Icon(imageVector = Icons.Outlined.Close, contentDescription = "")
            }
        },
        actions = {
            IconButton(onClick = {
                router.popBackStack()
            }) {
                Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = "")
            }
        }
    )
}

@Preview
@Composable
fun PreviewSendOverviewTopAppBar() {
    BaseContainer(
        topBar = { SendOverviewTopAppBar() }
    )
}