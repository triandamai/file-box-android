package app.trian.filebox.data.datasource.local.device

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DeviceDao {
    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insertDevice(vararg device: Device)

    @Query("SELECT * FROM device")
    fun getListDevices():List<Device>
}