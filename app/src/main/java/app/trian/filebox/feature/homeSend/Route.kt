package app.trian.filebox.feature.homeSend

import android.util.Log
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.FileBoxState
import app.trian.filebox.composables.decrementSelectedFileCount
import app.trian.filebox.composables.incrementSelectedFileCount
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
        SideEffect {
            Log.e("sas", selected.toString())
        }

        ScreenHomeSend(images = images,
            videos = videos,
            audios = audios,
            documents = documents,
            selectedFile = selected,
            onSelectedAudio = {
                appState.incrementSelectedFileCount()
            },
            onSelectedDocuments = {
                appState.incrementSelectedFileCount()
            },
            onSelectedImage = { image, isRemove ->
                with(appState) {
                    if (isRemove) {
                        viewModel.removeFile(image.uid)
                        decrementSelectedFileCount(
                            currentSize = selected.size
                        )
                    } else {
                        viewModel.addFile(image)
                        incrementSelectedFileCount(
                            currentSize = selected.size
                        )
                    }

                }
            },
            onSelectedVideo = {
                appState.incrementSelectedFileCount()
            })
    }
}
