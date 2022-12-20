package app.trian.filebox.data.datasource.local.selected

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "selected_file")
data class SelectedFile(
    @PrimaryKey
    @ColumnInfo
    val uid: Long,
    @ColumnInfo
    var name: String,
    @ColumnInfo
    var size: String,
    @ColumnInfo
    var date: String,
    @ColumnInfo
    var uri: String,
    @ColumnInfo
    var path: String,
    @ColumnInfo
    var mime: String
)