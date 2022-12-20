package app.trian.filebox.feature.homeSend

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.trian.filebox.data.datasource.local.selected.SelectedFile
import app.trian.filebox.domain.DeleteSelectedFileUseCase
import app.trian.filebox.domain.GetAudiosUseCase
import app.trian.filebox.domain.GetDocumentsUseCase
import app.trian.filebox.domain.GetImagesUseCase
import app.trian.filebox.domain.GetSelectedFileUseCase
import app.trian.filebox.domain.GetVideosUseCase
import app.trian.filebox.domain.SaveSelectedFileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeSendViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase,
    private val getVideosUseCase: GetVideosUseCase,
    private val getAudiosUseCase: GetAudiosUseCase,
    private val getDocumentsUseCase: GetDocumentsUseCase,
    private val getSelectedFileUseCase: GetSelectedFileUseCase,
    private val saveSelectedFileUseCase: SaveSelectedFileUseCase,
    private val deleteSelectedFileUseCase: DeleteSelectedFileUseCase,
) : ViewModel() {

    val images = getImagesUseCase().shareIn(
        scope = viewModelScope,
        replay = 1,
        started = SharingStarted.WhileSubscribed()
    )

    val videos = getVideosUseCase().shareIn(
        scope = viewModelScope,
        replay = 1,
        started = SharingStarted.WhileSubscribed()
    )

    val audios = getAudiosUseCase()
        .shareIn(
            scope = viewModelScope,
            replay = 1,
            started = SharingStarted.WhileSubscribed()
        )

    val documents = getDocumentsUseCase().shareIn(
        scope = viewModelScope,
        replay = 1,
        started = SharingStarted.WhileSubscribed()
    )

    val selectedFile = getSelectedFileUseCase().flowOn(Dispatchers.IO)


    fun addFile(selected: SelectedFile) = with(viewModelScope) {
        launch {
            Log.e("addFile", selected.toString())
            saveSelectedFileUseCase(selected)
        }
    }

    fun removeFile(id: Long) = with(viewModelScope) {
        launch {
            deleteSelectedFileUseCase(id)
        }
    }

}