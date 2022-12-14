/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.48
 */
package app.trian.filebox.feature.sendFileOverview

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.base.EventListener
import app.trian.filebox.base.FileBoxState
import app.trian.filebox.base.listener.ActionTopAppBar
import app.trian.filebox.data.models.DataState
import app.trian.filebox.feature.sendFileOverview.components.BottomSheetListDevice


object SendFileOverview {
    const val routeName = "send_file_overview"


}

@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.routeSendFileOverview(
    router: NavHostController,
    appState: FileBoxState,
    event: EventListener
) {
    composable(SendFileOverview.routeName) {
        val viewModel = hiltViewModel<SendFileOverviewViewModel>()

        val devices by viewModel.devices.collectAsState(initial = DataState.Loading)

        val scope = rememberCoroutineScope()
        val sheetState = rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.HalfExpanded,
            skipHalfExpanded = false,
            confirmStateChange = {
                it != ModalBottomSheetValue.Hidden
            }
        )
        LaunchedEffect(event) {
            with(event) {
                addTopAppBarEventListener { tag ->
                    when (tag) {
                        ActionTopAppBar.ACTION_NOTHING -> Unit
                        ActionTopAppBar.ACTION_NAV_BACK -> Unit
                        ActionTopAppBar.ACTION_SEND -> Unit
                        ActionTopAppBar.ACTION_SHARE_LINK -> Unit
                    }
                }
            }
        }
        ModalBottomSheetLayout(
            sheetState = sheetState,
            sheetBackgroundColor = MaterialTheme.colorScheme.surface,
            sheetContentColor = MaterialTheme.colorScheme.surface,
            scrimColor = Color.Transparent,
            sheetShape = RectangleShape,
            sheetElevation = 5.dp,
            sheetContent = {
                BottomSheetListDevice(
                    devices=devices,
                    isExpanded = sheetState.currentValue == ModalBottomSheetValue.Expanded,
                    onExpandClick = {}
                )

            }
        ) {

            ScreenSendFileOverview()

        }
    }
}
