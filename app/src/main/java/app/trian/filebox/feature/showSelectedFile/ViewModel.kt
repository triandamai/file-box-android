/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.48
 */
package app.trian.filebox.feature.showSelectedFile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.trian.filebox.data.datasource.local.selected.SelectedFile
import app.trian.filebox.data.models.DataState
import app.trian.filebox.domain.GetSelectedFileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowSelectedFileViewModel @Inject constructor(
    private val getSelectedFileUseCase: GetSelectedFileUseCase
) : ViewModel() {

    private val _selectedFile = MutableStateFlow<DataState<List<SelectedFile>>>(DataState.Loading)
    val selectedFile = _selectedFile.asStateFlow()

    init {
        getSelectedFile()
    }

    fun getSelectedFile() = with(viewModelScope) {
        launch {
            getSelectedFileUseCase().onEach {
                _selectedFile.tryEmit(it)
            }.collect()
        }
    }


}