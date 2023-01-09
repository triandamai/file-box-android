/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.49
 */
package app.trian.filebox.feature.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.base.EventListener
import app.trian.filebox.base.FileBoxState
import app.trian.filebox.base.extensions.showShortSnackbar
import app.trian.filebox.feature.signin.SignIn
import app.trian.filebox.feature.signup.SignUp.navigateToSignIn
import kotlinx.coroutines.launch

object SignUp {
    const val routeName = "sign_up"

    fun NavHostController.navigateToSignIn() {
        this.navigate(SignIn.routeName) {
            launchSingleTop = true
            popUpTo(routeName) {
                inclusive = true
            }
        }
    }


}

fun NavGraphBuilder.routeSignUp(
    router: NavHostController,
    appState: FileBoxState,
    event: EventListener
) {
    composable(SignUp.routeName) {
        val viewModel = hiltViewModel<SignUpViewModel>()
        val scope = rememberCoroutineScope()
        var isLoading by remember {
            mutableStateOf(false)
        }

        ScreenSignUp(
            goToSingIn = { router.navigateToSignIn() },
            isLoading = isLoading,
            onSubmit = { email, password, name ->
                with(viewModel) {
                    isLoading = true
                    signUp(email, password, name) { success, message ->
                        isLoading = false
                        scope.launch {
                            appState.showShortSnackbar(message)
                        }
                        if (success) {
                            router.navigateToSignIn()
                        }
                    }
                }
            }
        )
    }
}