package app.trian.filebox.feature.homeSend

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.base.FileBoxState
import app.trian.filebox.base.TAG_DELETE_ALL_SELECTED_FILE
import app.trian.filebox.base.updateSelectedFileCount
import app.trian.filebox.data.models.DataState


object HomeSend {
    const val routeName = "home_send"
}

fun NavGraphBuilder.routeHomeSend(
    router: NavHostController, appState: FileBoxState
) {
    composable(HomeSend.routeName) {
        val viewModel = hiltViewModel<HomeSendViewModel>()
        val images by viewModel.images.collectAsState(initial = DataState.Loading)
        val selected by viewModel.selectedFile.collectAsState(initial = listOf())

        LaunchedEffect(key1 = appState) {
            with(appState) {
                addBottomBarListener { tag, _ ->
                    when (tag) {
                        TAG_DELETE_ALL_SELECTED_FILE -> {
                            viewModel.clearSelectedFile()
                            updateSelectedFileCount(
                                currentSize = 0
                            )
                        }

                    }
                }
            }
        }
        LaunchedEffect(key1 = selected, block = {
            if (selected.isNotEmpty()) {
                appState.updateSelectedFileCount(
                    currentSize = selected.size
                )
            }
        })


        ScreenHomeSend(
            images = images,
            videos = DataState.Loading,
            audios = DataState.Loading,
            documents = DataState.Loading,
            selectedFile = selected,
            onFileClicked = { selectedFile, isRemove ->
                with(appState) {
                    if (isRemove) {
                        viewModel.removeFile(selectedFile.uid)
                        updateSelectedFileCount(
                            currentSize = selected.size - 1
                        )
                    } else {
                        viewModel.addFile(selectedFile)
                        updateSelectedFileCount(
                            currentSize = selected.size + 1
                        )
                    }

                }
            })
    }
}
