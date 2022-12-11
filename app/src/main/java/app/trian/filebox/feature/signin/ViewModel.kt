package app.trian.filebox.feature.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.trian.filebox.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    fun signInWithEmailAndPassword(
        email: String,
        password: String,
        callback: (Boolean, String) -> Unit = {_,_->}
    ) = viewModelScope.launch {
        userRepository.signInWithEmailAndPassword(email, password)
            .onStart {  }
            .onEach {
                callback(it.first,it.second)
            }
            .collect()

    }
}