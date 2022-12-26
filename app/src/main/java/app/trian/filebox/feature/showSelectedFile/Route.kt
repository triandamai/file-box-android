/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.48
 */
package app.trian.filebox.feature.showSelectedFile

import androidx.compose.foundation.layout.Box
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.base.EventListener
import app.trian.filebox.base.FileBoxState
import app.trian.filebox.base.listener.ActionAppState
import app.trian.filebox.base.listener.ActionTopAppBar
import app.trian.filebox.feature.sendFileOverview.SendFileOverview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


object ShowSelectedFile {
    const val routeName = "show_selected_file"


}

@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.routeShowSelectedFile(
    router: NavHostController,
    appState: FileBoxState,
    event: EventListener
) {
    composable(ShowSelectedFile.routeName) {
        val viewModel = hiltViewModel<ShowSelectedFileViewModel>()
        val selectedFile by viewModel.selectedFile.collectAsState()
        val scope = rememberCoroutineScope()
        var refreshing by remember { mutableStateOf(false) }

        fun refresh() = scope.launch {

            if(!refreshing){
                refreshing = true
                viewModel.getSelectedFile()
                delay(1000)
                refreshing = false
            }

        }

        val refreshState = rememberPullRefreshState(refreshing, ::refresh)

        LaunchedEffect(event){
            with(event){
                addTopAppBarEventListener{
                    tag->
                    when(tag){
                        ActionTopAppBar.ACTION_NOTHING -> Unit
                        ActionTopAppBar.ACTION_NAV_BACK -> Unit
                        ActionTopAppBar.ACTION_SEND -> {
                            router.navigate(SendFileOverview.routeName){
                                launchSingleTop = true
                            }
                        }
                        ActionTopAppBar.ACTION_SHARE_LINK -> Unit
                    }
                }
            }
        }


        Box(modifier = Modifier.pullRefresh(refreshState)){
            ScreenShowSelectedFile(
                data = selectedFile,
                onShowSelection = {
                    event.sendAppEvent(ActionAppState.SHOW_SELECTION, mapOf())
//                    appState.sendMessage(ActionAppState.SHOW_SELECTION)
                }
            )
            PullRefreshIndicator(refreshing, refreshState, Modifier.align(Alignment.TopCenter))
        }
    }
}
