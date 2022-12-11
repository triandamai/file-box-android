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
    this.navigate(SignUp.routeName){
        launchSingleTop = true
    }
}

fun NavHostController.navigateToHome(){
    this.navigate(Home.routeName){
        launchSingleTop = true
        popUpTo(SignIn.routeName){
            inclusive = true
        }
    }
}


fun NavGraphBuilder.routeSignIn(
    router: NavHostController,
    appState: FileBoxState
) {
    composable(SignIn.routeName){
        val viewModel = hiltViewModel<SignInViewModel>()
        ScreenSignIn(
            goToSignUp = { router.navigateToSignUp() },
            onSubmit = { _, _->router.navigateToHome()}
        )
    }
}