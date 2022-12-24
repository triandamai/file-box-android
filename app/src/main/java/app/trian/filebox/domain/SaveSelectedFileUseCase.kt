/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.46
 */
package app.trian.filebox.domain

import app.trian.filebox.data.datasource.local.selected.SelectedDao
import app.trian.filebox.data.datasource.local.selected.SelectedFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveSelectedFileUseCase @Inject constructor(
    private val selectedDao: SelectedDao
) {
    suspend operator fun invoke(
        data: SelectedFile
    )= withContext(Dispatchers.IO) {
        selectedDao.insertSelectedFile(data)
    }
}