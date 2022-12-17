package app.trian.filebox.feature.homeSend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.trian.filebox.data.datasource.local.images.ImagesFile
import app.trian.filebox.domain.GetImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _allFiles = MutableLiveData<Map<String, List<ImagesFile>>>()
    val allFiles: LiveData<Map<String, List<ImagesFile>>> get() = _allFiles


    fun getImages() = with(viewModelScope) {
        launch {
            getImagesUseCase().onEach {
                _allFiles.postValue(it)
            }.collect()
        }
    }

}