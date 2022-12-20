package app.trian.filebox.feature.signup

import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.base.FileBoxState
import app.trian.filebox.feature.signin.SignIn
import app.trian.filebox.feature.signup.SignUp.navigateToSignIn

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
    appState: FileBoxState
) {
    composable(SignUp.routeName) {
        val viewModel = hiltViewModel<SignUpViewModel>()
        val scope = rememberCoroutineScope()

        ScreenSignUp(
            goToSingIn = { router.navigateToSignIn() },
            onSubmit = { _, _ -> }
        )
    }
}