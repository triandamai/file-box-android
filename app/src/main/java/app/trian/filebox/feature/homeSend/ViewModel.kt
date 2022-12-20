package app.trian.filebox.feature.homeSend

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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
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

    private val _selectedFile = MutableStateFlow<List<Long>>(listOf())
    val selectedFile = _selectedFile.asStateFlow()

    fun loadSelectedFile() = with(viewModelScope) {
        launch {
            getSelectedFileUseCase().onEach {
                _selectedFile.tryEmit(it)
            }.collect()

        }
    }

    fun addFile(selected: SelectedFile) = with(viewModelScope) {
        launch {
            saveSelectedFileUseCase(selected)
            loadSelectedFile()
        }
    }

    fun removeFile(id: Long) = with(viewModelScope) {
        launch {
            deleteSelectedFileUseCase(id)
            loadSelectedFile()
        }
    }

}