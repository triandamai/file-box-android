package app.trian.filebox.data.datasource.local.audio

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AudioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAudios(vararg images: AudioFile)

    @Query("SELECT * FROM audio_file")
    fun getAll(): List<AudioFile>
}