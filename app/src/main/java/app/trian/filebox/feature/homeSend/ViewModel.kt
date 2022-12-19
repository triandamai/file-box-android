package app.trian.filebox.feature.homeSend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.trian.filebox.data.datasource.local.images.ImageFile
import app.trian.filebox.data.models.DataState
import app.trian.filebox.domain.GetImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeSendViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase
) : ViewModel() {

    init {
        getImages()
    }


    private val _images =
        MutableStateFlow<DataState<Map<String, List<ImageFile>>>>(DataState.Loading)
    val images = _images.asStateFlow()


    fun getImages() = with(viewModelScope) {
        launch {
            getImagesUseCase().onEach {
                _images.emit(it)
            }.collect()
        }
    }

}