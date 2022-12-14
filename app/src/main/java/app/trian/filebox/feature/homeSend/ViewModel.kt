/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.47
 */
package app.trian.filebox.feature.homeSend

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.trian.filebox.data.datasource.local.selected.SelectedFile
import app.trian.filebox.data.repository.StorageRepository
import app.trian.filebox.domain.DeleteSelectedFileUseCase
import app.trian.filebox.domain.GetImagesUseCase
import app.trian.filebox.domain.GetSelectedFileIdUseCase
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
    private val getSelectedFileIdUseCase: GetSelectedFileIdUseCase,
    private val saveSelectedFileUseCase: SaveSelectedFileUseCase,
    private val deleteSelectedFileUseCase: DeleteSelectedFileUseCase,
    private val storageRepository: StorageRepository
) : ViewModel() {

    val images = getImagesUseCase().shareIn(
        scope = viewModelScope,
        replay = 1,
        started = SharingStarted.WhileSubscribed()
    )

    private val _selectedFile = MutableStateFlow<List<Long>>(listOf())
    val selectedFile = _selectedFile.asStateFlow()

    init {
        loadSelectedFile()
    }

    fun loadSelectedFile() = with(viewModelScope) {
        launch {
            getSelectedFileIdUseCase().onEach {
                Log.e("hehe", it.toString())
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

    fun clearSelectedFile() = with(viewModelScope) {
        launch {
            storageRepository.clearSelectedFile()
            loadSelectedFile()
        }
    }

}