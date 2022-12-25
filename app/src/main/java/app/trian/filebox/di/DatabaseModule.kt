/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.45
 */
package app.trian.filebox.di

import android.content.Context
import androidx.room.Room
import app.trian.filebox.data.datasource.local.FileBoxDatabase
import app.trian.filebox.data.datasource.local.device.DeviceDao
import app.trian.filebox.data.datasource.local.selected.SelectedDao
import app.trian.filebox.data.datasource.local.sendFile.SendFileDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideLocalDatabase(
        @ApplicationContext appContext: Context,
    ): FileBoxDatabase = Room.databaseBuilder(
        appContext,
        FileBoxDatabase::class.java,
        FileBoxDatabase.dbName
    )
        .fallbackToDestructiveMigration()
        .build()


    @Provides
    fun provideSelectedDao(db: FileBoxDatabase): SelectedDao = db.selectedDao()

    @Provides
    fun provideSendFileDao(db:FileBoxDatabase):SendFileDao = db.sendFileDao()

    @Provides
    fun provideDeviceDao(db:FileBoxDatabase):DeviceDao = db.deviceDao()
}