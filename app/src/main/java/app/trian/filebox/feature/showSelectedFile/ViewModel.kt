package app.trian.filebox.feature.showSelectedFile

import androidx.lifecycle.ViewModel
import app.trian.filebox.data.repository.StorageRepository
import app.trian.filebox.domain.GetSelectedFileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShowSelectedFileViewModel @Inject constructor(
    private val getSelectedFileUseCase: GetSelectedFileUseCase,
    private val storageRepository: StorageRepository
) : ViewModel() {


}