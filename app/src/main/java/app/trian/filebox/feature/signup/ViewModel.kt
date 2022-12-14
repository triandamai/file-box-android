/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.49
 */
package app.trian.filebox.feature.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.trian.filebox.domain.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    fun signUp(
        email: String,
        password: String,
        userName: String,
        callback: (Boolean, String) -> Unit
    ) = with(viewModelScope) {
        launch {
            signUpUseCase(email, password, userName)
                .onEach {
                    callback(it.first, it.second)
                }
                .collect()
        }
    }
}