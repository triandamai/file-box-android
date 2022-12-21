package app.trian.filebox.feature.homeSend

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.base.BottomBarType
import app.trian.filebox.base.FileBoxState
import app.trian.filebox.base.SnackBarType
import app.trian.filebox.base.TAG_ACTION_MORE
import app.trian.filebox.base.TAG_ACTION_SEND
import app.trian.filebox.base.updateSelectedFileCount
import app.trian.filebox.data.models.DataState
import app.trian.filebox.feature.homeSend.components.BottomSheetOption
import app.trian.filebox.feature.showSelectedFile.ShowSelectedFile
import kotlinx.coroutines.launch


object HomeSend {
    const val routeName = "home_send"
}

@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.routeHomeSend(
    router: NavHostController,
    appState: FileBoxState
) {
    composable(HomeSend.routeName) {
        val viewModel = hiltViewModel<HomeSendViewModel>()
        val images by viewModel.images.collectAsState(initial = DataState.Loading)
        val selected by viewModel.selectedFile.collectAsState(initial = listOf())
        val scope = rememberCoroutineScope()
        val sheetState = rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden,
            skipHalfExpanded = true,
            confirmStateChange = {
                if (it == ModalBottomSheetValue.Hidden) {
                    appState.showBottomBar(BottomBarType.BASIC)
                    if (selected.isNotEmpty()) {
                        scope.launch {
                            appState.showSnackbar(
                                "",
                                SnackBarType.PICK_FILE
                            )

                        }
                    }
                }
                true
            }
        )

        LaunchedEffect(appState, selected,sheetState) {
            with(appState) {
                addSnackbarListener { tag, _ ->
                    when (tag) {
                        TAG_ACTION_MORE -> {
                            scope.launch {
                                appState.hideSnackbar()
                                appState.hideBottomBar()
                                sheetState.animateTo(ModalBottomSheetValue.Expanded)
                                sheetState.show()
                            }
                        }
                        TAG_ACTION_SEND -> {
                            scope.launch {
                                appState.hideSnackbar()
                            }
                        }
                    }
                }

            }
            if (selected.isNotEmpty()) {
                scope.launch {
                    appState.selectedFileCount = selected.size
                    appState.showSnackbar("", SnackBarType.PICK_FILE)
                }
            }
        }

        ModalBottomSheetLayout(
            sheetState = sheetState, sheetContent = {
                BottomSheetOption(
                    onViewList = {
                        router.navigate(ShowSelectedFile.routeName){
                            launchSingleTop = true
                        }
                    }
                )
            }) {
            ScreenHomeSend(
                images = images,
                videos = DataState.Loading,
                audios = DataState.Loading,
                documents = DataState.Loading,
                selectedFile = selected,
                onFileClicked = { selectedFile, isRemove ->
                    with(appState) {
                        val size = if (isRemove) selected.size - 1 else selected.size + 1
                        updateSelectedFileCount(size)

                        scope.launch {
                            if (isRemove) {
                                if (size == 0) {
                                    appState.hideSnackbar()
                                }
                            } else {
                                if (size == 1) {
                                    Log.e("sasa", "sasa")
                                    appState.showSnackbar("", SnackBarType.PICK_FILE)
                                }
                            }
                        }
                        if (isRemove) {
                            viewModel.removeFile(selectedFile.uid)
                        } else {
                            viewModel.addFile(selectedFile)
                        }
                    }
                })
        }
    }


}
