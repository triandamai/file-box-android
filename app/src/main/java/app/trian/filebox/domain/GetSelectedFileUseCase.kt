package app.trian.filebox.domain

import app.trian.filebox.data.datasource.local.selected.SelectedDao
import javax.inject.Inject

class GetSelectedFileUseCase @Inject constructor(
    private val selectedDao: SelectedDao
) {

    operator fun invoke() = selectedDao.getAllId()
}