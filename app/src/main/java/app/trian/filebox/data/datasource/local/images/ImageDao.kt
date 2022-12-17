package app.trian.filebox.data.datasource.local.images

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(vararg images: ImageFile)

    @Query("SELECT * FROM images_file")
    fun getAll(): List<ImageFile>
}