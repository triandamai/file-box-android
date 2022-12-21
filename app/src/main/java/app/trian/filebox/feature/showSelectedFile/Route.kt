package app.trian.filebox.feature.showSelectedFile

import androidx.compose.foundation.layout.Box
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
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
import app.trian.filebox.base.FileBoxState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


object ShowSelectedFile {
    const val routeName = "show_selected_file"


}

@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.routeShowSelectedFile(
    router: NavHostController,
    appState: FileBoxState
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


        Box(modifier = Modifier.pullRefresh(refreshState)){
            ScreenShowSelectedFile(
                data = selectedFile
            )
            PullRefreshIndicator(refreshing, refreshState, Modifier.align(Alignment.TopCenter))
        }
    }
}
