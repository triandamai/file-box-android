/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.45
 */
package app.trian.filebox.data.repository

import app.trian.filebox.data.AuthException
import app.trian.filebox.data.models.DeviceModel
import app.trian.filebox.data.models.toFirestore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
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
    override suspend fun isLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
        deviceModel: DeviceModel
    ): Flow<Pair<Boolean, String>> = flow {
        try {
            val credential = firebaseAuth.signInWithEmailAndPassword(email, password).await()
                ?: throw AuthException("Sign in failed")

            val deviceRef = firebaseFirestore.collection("USER")
                .document(credential.user?.uid.orEmpty())
                .collection("DEVICE")
                .document(deviceModel.deviceUnique)

            val exist = deviceRef.get().await().exists()
            if (!exist) {
                deviceRef.set(
                    deviceModel.toFirestore(),
                    SetOptions.merge()
                ).await()
            }
            emit(Pair(true, "Success signin"))
        } catch (e: Exception) {
            throw  AuthException(e.message.orEmpty())
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun signUpWithEmailAndPassword(
        email: String,
        password: String,
        deviceModel: DeviceModel
    ): Flow<Pair<Boolean, String>> = flow {
        try {
            val credential = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
                ?: throw  AuthException("Authentication failed")
            //only accept new user
            if (credential.additionalUserInfo?.isNewUser != true) {
                firebaseAuth.signOut()
                throw AuthException("User already exist!")
            }

            firebaseFirestore.collection("USER")
                .document(credential.user?.uid.orEmpty())
                .collection("DEVICE")
                .document(deviceModel.deviceUnique)
                .set(
                    deviceModel.toFirestore(),
                    SetOptions.merge()
                )
                .await()

            emit(Pair(true, "Success register"))
        } catch (e: Exception) {
            throw  AuthException(e.message.orEmpty())
        }
    }.flowOn(Dispatchers.IO)

}