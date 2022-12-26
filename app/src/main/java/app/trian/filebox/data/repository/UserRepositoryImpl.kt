/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.45
 */
package app.trian.filebox.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) : UserRepository {
    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Pair<Boolean, String>> = flow {
        try {
            val credential = firebaseAuth.signInWithEmailAndPassword(email,password).await()
            if(credential.user != null){
                emit(Pair(false,""))
            }else{
                emit(Pair(true,""))
            }
        }catch (e:Exception){
            emit(Pair(false,e.message.orEmpty()))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun signUpWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Pair<Boolean, String>> = flow {
        try {
            val credential = firebaseAuth.createUserWithEmailAndPassword(email,password).await()
            if(credential.user != null){
                emit(Pair(false,""))
            }else{
                emit(Pair(true,""))
            }
        }catch (e:Exception){
            emit(Pair(false,e.message.orEmpty()))
        }
    }.flowOn(Dispatchers.IO)

}