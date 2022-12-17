package app.trian.filebox.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import app.trian.filebox.data.datasource.local.audio.AudioFile
import app.trian.filebox.data.datasource.local.images.ImagesDao
import app.trian.filebox.data.datasource.local.images.ImagesFile
import app.trian.filebox.data.datasource.local.videos.VideosDao
import app.trian.filebox.data.datasource.local.videos.VideosFile

@Database(
    entities = [
        VideosFile::class,
        AudioFile::class,
        ImagesFile::class
    ],
    version = 2,
    exportSchema = true
)
abstract class FileBoxDatabase : RoomDatabase() {
    abstract fun imagesDao(): ImagesDao
    abstract fun videosDao(): VideosDao


    companion object {
        const val dbName = "filebox_trian_app"
    }
}