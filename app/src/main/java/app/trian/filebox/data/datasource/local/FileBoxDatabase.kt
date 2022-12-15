package app.trian.filebox.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import app.trian.filebox.data.datasource.local.images.ImagesDao
import app.trian.filebox.data.datasource.local.images.ImageFile

@Database(
    entities = [ImageFile::class],
    version = 1
)
abstract class FileBoxDatabase:RoomDatabase() {
    abstract fun audioDao(): ImagesDao

    companion object{
        const val dbName = "app.trian.filebox"
    }
}