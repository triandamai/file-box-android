package app.trian.filebox.data.datasource.local.images

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ImagesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(vararg images: ImagesFile)

    @Query("SELECT * FROM images_file")
    fun getAll(): List<ImagesFile>
}