package app.trian.filebox.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import app.trian.filebox.data.datasource.local.selected.SelectedDao
import app.trian.filebox.data.datasource.local.selected.SelectedFile

@Database(
    entities = [
        SelectedFile::class
    ],
    version = 4,
    exportSchema = true
)
abstract class FileBoxDatabase : RoomDatabase() {
    abstract fun selectedDao(): SelectedDao


    companion object {
        const val dbName = "filebox_trian_app"
    }
}