/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.45
 */
package app.trian.filebox.domain

import app.trian.filebox.data.datasource.local.selected.SelectedDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteSelectedFileUseCase @Inject constructor(
    private val selectedDao: SelectedDao
) {
    suspend operator fun invoke(
        id: Long
    ) = withContext(Dispatchers.IO){
        selectedDao.delete(id)
    }
}