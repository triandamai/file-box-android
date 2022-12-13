package app.trian.filebox.data

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
        firebaseFirestore.collection("").get().await()
        firebaseAuth.currentUser
        emit(Pair(false, "gagall"))
    }.flowOn(Dispatchers.IO)

}