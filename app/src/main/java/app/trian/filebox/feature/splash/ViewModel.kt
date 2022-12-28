package app.trian.filebox.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.trian.filebox.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    fun isLoggedIn(callback: (Boolean) -> Unit) = with(viewModelScope) {
        launch {
            val isLoggedIn = userRepository.isLoggedIn()
            callback(isLoggedIn)
        }
    }
}