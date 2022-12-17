package app.trian.filebox.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import app.trian.filebox.data.datasource.local.audio.AudioDao
import app.trian.filebox.data.datasource.local.audio.AudioFile
import app.trian.filebox.data.datasource.local.documents.DocumentDao
import app.trian.filebox.data.datasource.local.documents.DocumentFile
import app.trian.filebox.data.datasource.local.images.ImageDao
import app.trian.filebox.data.datasource.local.images.ImageFile
import app.trian.filebox.data.datasource.local.videos.VideosDao
import app.trian.filebox.data.datasource.local.videos.VideosFile

@Database(
    entities = [
        VideosFile::class,
        AudioFile::class,
        ImageFile::class,
        DocumentFile::class
    ],
    version = 2,
    exportSchema = true
)
abstract class FileBoxDatabase : RoomDatabase() {
    abstract fun imagesDao(): ImageDao
    abstract fun videosDao(): VideosDao
    abstract fun audioDao(): AudioDao
    abstract fun documentDao(): DocumentDao


    companion object {
        const val dbName = "filebox_trian_app"
    }
}