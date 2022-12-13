package app.trian.filebox.feature.detailFolder

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailFolderViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
):ViewModel(){

    init {
        val folderId = savedStateHandle.get<String>(DetailFolder.argKey).orEmpty()
        if (folderId.isNotBlank()){
            //TODO
        }
    }
}