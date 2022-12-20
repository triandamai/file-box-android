package app.trian.filebox.feature.homeSend

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.base.BottomBarListener
import app.trian.filebox.base.FileBoxState
import app.trian.filebox.base.TAG_FAB
import app.trian.filebox.base.decrementSelectedFileCount
import app.trian.filebox.base.incrementSelectedFileCount
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
        val videos by viewModel.videos.collectAsState(initial = DataState.Loading)
        val audios by viewModel.audios.collectAsState(initial = DataState.Loading)
        val documents by viewModel.documents.collectAsState(initial = DataState.Loading)
        val selected by viewModel.selectedFile.collectAsState(initial = listOf())

        LaunchedEffect(key1 = appState) {
            with(appState) {
                addBottomBarListener(object : BottomBarListener {
                    override fun onItemClicked(tag: String, data: Map<String, String>) {
                        if(tag == TAG_FAB){

                        }
                    }

                })
            }
        }

        ScreenHomeSend(images = images,
            videos = videos,
            audios = audios,
            documents = documents,
            selectedFile = selected,
            onFileClicked = { selectedFile, isRemove ->
                with(appState) {
                    if (isRemove) {
                        viewModel.removeFile(selectedFile.uid)
                        decrementSelectedFileCount(
                            currentSize = selected.size - 1
                        )
                    } else {
                        viewModel.addFile(selectedFile)
                        incrementSelectedFileCount(
                            currentSize = selected.size + 1
                        )
                    }

                }
            })
    }
}
