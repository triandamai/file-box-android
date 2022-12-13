package app.trian.filebox.feature.detailFile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailFileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
):ViewModel(){
init {
    val fileId = savedStateHandle.get<String>(DetailFile.argKey).orEmpty()
    if(fileId.isNotBlank()){
        //TODO
    }
}
}