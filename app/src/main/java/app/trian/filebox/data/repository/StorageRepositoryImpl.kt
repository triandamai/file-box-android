/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.44
 */
package app.trian.filebox.data.repository

import android.content.Context
import app.trian.filebox.data.datasource.StorageDataSource
import app.trian.filebox.data.datasource.local.selected.SelectedDao
import app.trian.filebox.data.datasource.local.selected.SelectedFile
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class StorageRepositoryImpl @Inject constructor(
    @ApplicationContext val appContext: Context,
    private val selectedDao: SelectedDao
) : StorageRepository {
    override suspend fun getSelectedFile(): Flow<List<SelectedFile>> = selectedDao.getAll()

    override suspend fun getAllImagesFromStorage() = StorageDataSource().getImages(appContext)

    override suspend fun clearSelectedFile() = withContext(Dispatchers.IO) {
        selectedDao.deleteAll()
    }

}