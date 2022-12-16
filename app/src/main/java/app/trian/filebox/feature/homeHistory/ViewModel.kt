package app.trian.filebox.feature.homeHistory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.trian.filebox.data.domain.GetAllFilesUseCase
import app.trian.filebox.data.models.FileModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeHistoryViewModel @Inject constructor(
    private val getAllFilesUseCase: GetAllFilesUseCase
) : ViewModel() {

    private val _allFiles = MutableLiveData<Map<String, List<FileModel>>>()
    val allFiles: LiveData<Map<String, List<FileModel>>> get() = _allFiles


    init {
        getAllFiles()
    }

    fun getAllFiles() = with(viewModelScope) {
        launch {

            getAllFilesUseCase().onEach {
                Log.e("sasa",it.toString())
                    _allFiles.postValue(it)
                }.collect()
        }
    }

}