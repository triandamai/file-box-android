/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.45
 */
package app.trian.filebox.data.repository

import app.trian.filebox.data.models.DeviceModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun isLoggedIn():Boolean
    suspend fun signInWithEmailAndPassword(
        email:String,
        password:String,
        deviceModel:DeviceModel
    ):Flow<Pair<Boolean,String>>

    suspend fun signUpWithEmailAndPassword(
        email:String,
        password: String,
        userName:String,
        deviceModel: DeviceModel
    ):Flow<Pair<Boolean,String>>
}