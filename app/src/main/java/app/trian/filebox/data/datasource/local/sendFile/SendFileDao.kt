/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.43
 */
package app.trian.filebox.data.datasource.local.sendFile

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SendFileDao {

    @Insert
    fun insertSendFile(vararg sendFile: SendFile)

    @Insert
    fun insertDetailSendFile(vararg sendFileDetail: SendFileDetail)

    @Query("SELECT * FROM send_file " +
            "JOIN send_file_detail ON send_file.sendId = send_file_detail.sendId")
    fun getSendFile():Map<SendFile,SendFileDetail>

    @Query("SELECT * FROM send_file " +
            "JOIN send_file_detail ON send_file.sendId = send_file_detail.sendId " +
            "WHERE send_file.uid = :id")
    fun getSendFileById(id: Long):Map<SendFile,SendFileDetail>

    @Query("DELETE FROM send_file WHERE sendId=:sendId")
    fun deleteSendFile(sendId:String)

    @Query("DELETE FROM send_file_detail WHERE sendId=:sendId")
    fun deleteSendFileDetailBySendId(sendId:String)
}