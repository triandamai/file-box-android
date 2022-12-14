package app.trian.filebox.data.repository

import android.os.Build
import app.trian.filebox.data.datasource.local.device.Device
import app.trian.filebox.data.datasource.local.device.DeviceDao
import app.trian.filebox.data.models.DataState
import app.trian.filebox.data.models.DeviceModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.installations.FirebaseInstallations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceRepositoryImpl @Inject constructor(
    private val deviceDao: DeviceDao,
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseInstallations: FirebaseInstallations
) : DeviceRepository {
    override suspend fun getDeviceUniqueId():DeviceModel? {
        return try {
            val id = firebaseInstallations.id.await()
            DeviceModel(
                deviceId = id,
                deviceName = Build.MODEL,
                deviceUnique = id,
                isDelete = false
            )
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getDevices(): Flow<DataState<List<Device>>> = flow {
        emit(DataState.Loading)
        val devices = deviceDao.getListDevices()
        if (devices.isEmpty()) {
            emit(DataState.Empty)
        } else {
            emit(DataState.Data(devices))
        }
    }.catch {
        emit(DataState.Error(it.message.orEmpty()))
    }.flowOn(Dispatchers.IO)

    override suspend fun syncDeviceFromCloud(): Flow<List<DeviceModel>> = flow {
        val auth = firebaseAuth.currentUser ?: throw FirebaseAuthException("user not logged in", "")

        val listDevices = firebaseFirestore.collection("USER").document(auth.uid)
            .collection("DEVICE")
            .get()
            .await()

        val data = listDevices.documents.map {
            it.toObject(DeviceModel::class.java)!!
        }
        emit(data)

    }.flowOn(Dispatchers.IO)

    override suspend fun insertDevice(device: List<Device>): Flow<Pair<Boolean, String>> = flow {

        deviceDao.insertDevice(
            * device.toTypedArray()
        )

        emit(Pair(true, ""))
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteDevice(device: List<Device>): Flow<Pair<Boolean, String>> =
        flow {
            deviceDao.deleteDevices(
                *device.toTypedArray()
            )
            emit(Pair(true, ""))
        }.flowOn(Dispatchers.IO)
}