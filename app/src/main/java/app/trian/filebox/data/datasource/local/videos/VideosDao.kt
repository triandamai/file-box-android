package app.trian.filebox.data.datasource.local.videos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface VideosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideos(vararg images: VideosFile)
}