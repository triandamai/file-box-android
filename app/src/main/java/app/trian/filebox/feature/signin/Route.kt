package app.trian.filebox.feature.signin

import android.Manifest
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
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
import app.trian.filebox.composables.BottomBarListener
import app.trian.filebox.feature.home.Home
import app.trian.filebox.feature.signup.SignUp
import kotlinx.coroutines.launch
import java.security.Permission


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

        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions(),
            onResult = {data->
                Log.e("sas",data.toString())
                val isGranted1 = data[Manifest.permission.READ_EXTERNAL_STORAGE]!!
                val isGranted = data[Manifest.permission.READ_EXTERNAL_STORAGE]!!
                if(isGranted && isGranted1) {
                    viewModel.readFile()
                }
            }
        )

        LaunchedEffect(key1 = Unit, block = {
            scope.launch {
                launcher.launch(arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ))
            }
        })



        ScreenSignIn(
            goToSignUp = { router.navigateToSignUp() },
            onSubmit = { email, password->
                isLoading = true
                router.navigateToHome()
//                viewModel.signInWithEmailAndPassword(email, password){
//                    success,message->
//                    if(success){
//                        router.navigateToHome()
//                    }else{
//                        scope.launch {
//                            appState.snackbarHostState.showSnackbar(message)
//                        }
//                    }
//                }
            }
        )
    }
}