/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.45
 */
package app.trian.filebox.data.repository

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun signInWithEmailAndPassword(
        email:String,
        password:String
    ):Flow<Pair<Boolean,String>>
}