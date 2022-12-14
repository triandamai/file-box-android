package app.trian.filebox.data.repository

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun signInWithEmailAndPassword(
        email:String,
        password:String
    ):Flow<Pair<Boolean,String>>
}