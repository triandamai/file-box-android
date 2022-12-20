package app.trian.filebox.domain

import app.trian.filebox.data.datasource.local.selected.SelectedDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetSelectedFileUseCase @Inject constructor(
    private val selectedDao: SelectedDao
) {

    operator fun invoke() = selectedDao.getAllId()
}