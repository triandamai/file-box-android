package app.trian.filebox.data.repository

import android.content.Context
import app.trian.filebox.data.datasource.StorageDataSource
import app.trian.filebox.data.models.FileModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class StorageRepositoryImpl @Inject constructor(
    @ApplicationContext val appContext: Context
) : StorageRepository {
    override suspend fun getAllFiles(): Map<String, List<FileModel>> {
        TODO("Not yet implemented")
    }
    //https://stackoverflow.com/questions/62782648/android-11-scoped-storage-permissions
    // in android 10 or higher MediaStore.Files. have restriction(scoped storage) only  show vide,image , and video
//    @SuppressLint("InlinedApi")
//    override suspend fun getAllFiles(): Map<String, List<FileModel>> =
//        StorageDataSource().getAllFiles(appContext).groupBy { it.path }

    override suspend fun getImages(): Flow<List<FileModel>> =
        StorageDataSource().getImages(appContext)
}