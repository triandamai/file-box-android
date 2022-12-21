package app.trian.filebox.feature.pickSendTarget

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.trian.filebox.data.repository.StorageRepository
import app.trian.filebox.domain.GetSelectedFileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SendTargetViewModel @Inject constructor(
    private val getSelectedFileUseCase: GetSelectedFileUseCase,
    private val storageRepository: StorageRepository
) : ViewModel() {

    private val _selectedFile = MutableStateFlow<List<Long>>(listOf())
    val selectedFile = _selectedFile.asStateFlow()


    init {
        loadSelectedFile()
    }

    fun loadSelectedFile() = with(viewModelScope) {
        launch {
            getSelectedFileUseCase().onEach {
                Log.e("hehe", it.toString())
                _selectedFile.tryEmit(it)
            }.collect()

        }
    }

    fun clearSelectedFile() = with(viewModelScope) {
        launch {
            storageRepository.clearSelectedFile()
            loadSelectedFile()
        }
    }
}