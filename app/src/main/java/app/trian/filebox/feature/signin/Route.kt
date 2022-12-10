package app.trian.filebox.feature.signin

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.FileBoxState
import app.trian.filebox.feature.home.Home
import app.trian.filebox.feature.signup.SignUp


object SignIn{
    const val routeName = "sign_in"
}

fun NavHostController.navigateToSignUp(){
    this.navigate(SignUp.routeName)
}

fun NavHostController.navigateToHome(){
    this.navigate(Home.routeName)
}


fun NavGraphBuilder.routeSignIn(
    router: NavHostController,
    appState: FileBoxState
) {
    composable(SignIn.routeName){
        val viewModel = hiltViewModel<SignInViewModel>()
        ScreenSignIn(
            goToSignUp = { router.navigateToSignUp() },
            onLogin = {_,_->router.navigateToHome()}
        )
    }
}