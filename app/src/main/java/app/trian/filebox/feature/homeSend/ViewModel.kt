package app.trian.filebox.feature.homeSend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.trian.filebox.data.datasource.local.audio.AudioFile
import app.trian.filebox.data.datasource.local.documents.DocumentFile
import app.trian.filebox.data.datasource.local.images.ImageFile
import app.trian.filebox.data.datasource.local.videos.VideosFile
import app.trian.filebox.data.models.DataState
import app.trian.filebox.domain.GetAudiosUseCase
import app.trian.filebox.domain.GetDocumentsUseCase
import app.trian.filebox.domain.GetImagesUseCase
import app.trian.filebox.domain.GetVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeSendViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase,
    private val getVideosUseCase: GetVideosUseCase,
    private val getAudiosUseCase: GetAudiosUseCase,
    private val getDocumentsUseCase: GetDocumentsUseCase
) : ViewModel() {

    private val _images =
        MutableStateFlow<DataState<Map<String, List<ImageFile>>>>(DataState.Loading)
    val images = _images.asStateFlow()

    private val _videos =
        MutableStateFlow<DataState<Map<String, List<VideosFile>>>>(DataState.Loading)
    val videos = _videos.asStateFlow()

    private val _audio =
        MutableStateFlow<DataState<Map<String, List<AudioFile>>>>(DataState.Loading)
    val audios = _audio.asStateFlow()

    private val _documents =
        MutableStateFlow<DataState<Map<String, List<DocumentFile>>>>(DataState.Loading)
    val documents = _documents.asStateFlow()

    init {
        getImages()
        getAudio()
        getVideos()
        getDocuments()
    }

    fun getImages() = with(viewModelScope) {
        _images.tryEmit(DataState.Loading)
        launch {
            getImagesUseCase().onEach {
                _images.tryEmit(it)
            }.collect()
        }
    }

    fun getVideos() = with(viewModelScope) {
        _videos.tryEmit(DataState.Loading)
        launch {
            getVideosUseCase().onEach {
                _videos.tryEmit(it)
            }.collect()
        }
    }

    fun getAudio() = with(viewModelScope) {
        _audio.tryEmit(DataState.Loading)
        launch {
            getAudiosUseCase().onEach {
                _audio.tryEmit(it)
            }.collect()
        }
    }

    fun getDocuments() = with(viewModelScope){
        _documents.tryEmit(DataState.Loading)
        launch {
            getDocumentsUseCase().onEach {
                _documents.tryEmit(it)
            }.collect()
        }
    }

}