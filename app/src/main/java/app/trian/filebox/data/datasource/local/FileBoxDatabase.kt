/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.43
 */
package app.trian.filebox.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import app.trian.filebox.data.datasource.local.selected.SelectedDao
import app.trian.filebox.data.datasource.local.selected.SelectedFile
import app.trian.filebox.data.datasource.local.sendFile.SendFile
import app.trian.filebox.data.datasource.local.sendFile.SendFileDetail

@Database(
    entities = [
        SelectedFile::class,
        SendFile::class,
        SendFileDetail::class
    ],
    version = 5,
    exportSchema = true
)
abstract class FileBoxDatabase : RoomDatabase() {
    abstract fun selectedDao(): SelectedDao


    companion object {
        const val dbName = "filebox_trian_app"
    }
}