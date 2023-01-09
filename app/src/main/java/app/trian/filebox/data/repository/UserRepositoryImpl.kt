/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.45
 */
package app.trian.filebox.data.repository

import app.trian.filebox.R
import app.trian.filebox.composables.FirebaseConstants.COLLECTION_DEVICE
import app.trian.filebox.composables.FirebaseConstants.COLLECTION_USER
import app.trian.filebox.composables.GetStringResources
import app.trian.filebox.data.AuthException
import app.trian.filebox.data.models.DeviceModel
import app.trian.filebox.data.models.toFirestore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
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
    private val firebaseFirestore: FirebaseFirestore,
    private val getString: GetStringResources
) : UserRepository {
    override suspend fun isLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
        deviceModel: DeviceModel
    ): Flow<Pair<Boolean, String>> = flow {
        with(firebaseAuth) {
            try {
                val credential = signInWithEmailAndPassword(email, password).await()
                    ?: throw AuthException(getString(R.string.message_sign_in_failed))

                //abort when user is not verified the email yet
                if (credential.user?.isEmailVerified != true) {
                    signOut()
                    throw throw AuthException(getString(R.string.message_sign_in_email_not_verified))
                }
                //check whether current device exist or no
                val deviceRef = firebaseFirestore
                    .collection(COLLECTION_USER)
                    .document(credential.user?.uid.orEmpty())
                    .collection(COLLECTION_DEVICE)
                    .document(deviceModel.deviceUnique)

                val exist = deviceRef.get().await().exists()
                //if device isn't exist save as new device active
                if (!exist) {
                    deviceRef.set(
                        deviceModel.toFirestore(),
                        SetOptions.merge()
                    ).await()
                }
                emit(Pair(true, getString(R.string.message_sign_in_success)))
            } catch (e: Exception) {
                throw  AuthException(e.message.orEmpty())
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun signUpWithEmailAndPassword(
        email: String,
        password: String,
        userName: String,
        deviceModel: DeviceModel
    ): Flow<Pair<Boolean, String>> = flow {
        with(firebaseAuth) {
            try {
                val credential = createUserWithEmailAndPassword(email, password).await()
                    ?: throw  AuthException(getString(R.string.message_authentication_failed))
                //only accept new user
                if (credential.additionalUserInfo?.isNewUser != true) {
                    signOut()
                    throw AuthException(getString(R.string.message_sign_up_user_exist))
                }

                //update profile user
                currentUser?.updateProfile(userProfileChangeRequest {
                    displayName = userName
                })

                //save current device as active device
                firebaseFirestore
                    .collection(COLLECTION_USER)
                    .document(credential.user?.uid.orEmpty())
                    .collection(COLLECTION_DEVICE)
                    .document(deviceModel.deviceUnique)
                    .set(
                        deviceModel.toFirestore(),
                        SetOptions.merge()
                    )
                    .await()

                //send email verification and clear session then user can continue sign in
                currentUser?.sendEmailVerification()?.await()
                signOut()

                emit(Pair(true, getString(R.string.message_sign_up_success)))
            } catch (e: Exception) {
                throw  AuthException(e.message.orEmpty())
            }
        }

    }.flowOn(Dispatchers.IO)

}