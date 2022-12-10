package app.trian.filebox.feature.signup

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.FileBoxState
import app.trian.filebox.feature.signin.SignIn

object SignUp{
    const val routeName = "sign_up"
}
fun NavHostController.navigateToSignIn(){
    this.navigate(SignIn.routeName,)
}

fun NavGraphBuilder.routeSignUp(
    router: NavHostController,
    appState: FileBoxState
) {
    composable(SignUp.routeName){
        val viewModel = hiltViewModel<SignUpViewModel>()
        ScreenSignUp(
            goToSingIn = {router.navigateToSignIn()}
        )
    }
}