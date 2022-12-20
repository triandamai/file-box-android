package app.trian.filebox.data.datasource.local.documents

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.trian.filebox.data.datasource.local.selected.SelectedFile

@Dao
interface DocumentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDocuments(vararg images: DocumentFile)

    @Query("SELECT * FROM document_file")
    fun getAll(): List<DocumentFile>
}