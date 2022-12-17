package app.trian.filebox.di

import android.content.Context
import androidx.room.Room
import app.trian.filebox.data.datasource.local.FileBoxDatabase
import app.trian.filebox.data.datasource.local.audio.AudioDao
import app.trian.filebox.data.datasource.local.images.ImagesDao
import app.trian.filebox.data.datasource.local.videos.VideosDao
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
        @ApplicationContext appContext:Context,
    ):FileBoxDatabase = Room.databaseBuilder(
        appContext,
        FileBoxDatabase::class.java,
        FileBoxDatabase.dbName
    ).build()

    @Provides
    fun provideImagesDao(db:FileBoxDatabase):ImagesDao = db.imagesDao()

    @Provides
    fun provideVideosDao(db:FileBoxDatabase):VideosDao = db.videosDao()

    @Provides
    fun provideAudiosDao(db:FileBoxDatabase):AudioDao = db.audioDao()
}