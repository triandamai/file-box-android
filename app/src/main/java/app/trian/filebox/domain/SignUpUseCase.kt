package app.trian.filebox.domain

import app.trian.filebox.data.repository.DeviceRepository
import app.trian.filebox.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val deviceRepository: DeviceRepository,
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String) =
        channelFlow {

            val device = deviceRepository.getDeviceUniqueId()
            if (device != null) {
                userRepository
                    .signUpWithEmailAndPassword(
                        email,
                        password,
                        device
                    )
                    .onEach {
                        send(it)
                    }
                    .catch {
                        send(Pair(false, it.message.orEmpty()))
                    }
                    .collect()
            } else {
                send(Pair(false, "Failed to recognized the device"))
            }

        }.flowOn(Dispatchers.IO)
}