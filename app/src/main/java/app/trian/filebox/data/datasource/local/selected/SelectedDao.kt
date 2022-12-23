package app.trian.filebox.data.datasource.local.selected

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SelectedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSelectedFile(vararg data: SelectedFile)

    @Query("SELECT * FROM selected_file")
    fun getAll(): Flow<List<SelectedFile>>

    @Query("DELETE FROM selected_file WHERE uid=:id")
    fun delete(id:Long)

    @Query("DELETE FROM selected_file")
    fun deleteAll()
}