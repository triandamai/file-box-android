package app.trian.filebox.feature.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.FileBoxState
import app.trian.filebox.feature.home.Home
import app.trian.filebox.feature.signup.SignUp
import kotlinx.coroutines.launch


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
        var isLoading by remember {
            mutableStateOf(false)
        }
        val scope = rememberCoroutineScope()

        ScreenSignIn(
            goToSignUp = { router.navigateToSignUp() },
            onSubmit = { email, password->
                isLoading = true
                viewModel.signInWithEmailAndPassword(email, password){
                    success,message->
                    if(success){
                        router.navigateToHome()
                    }else{
                        scope.launch {
                            appState.snackbarHostState.showSnackbar(message)
                        }
                    }
                }
            }
        )
    }
}