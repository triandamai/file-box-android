package app.trian.filebox.feature.signin

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.FileBoxState
import app.trian.filebox.composables.checkPermissionManageStorage
import app.trian.filebox.composables.permissionReadWrite
import app.trian.filebox.feature.homeSend.HomeSend
import app.trian.filebox.feature.signin.SignIn.navigateToHome
import app.trian.filebox.feature.signin.SignIn.navigateToSignUp
import app.trian.filebox.feature.signup.SignUp


object SignIn {
    const val routeName = "sign_in"

    fun NavHostController.navigateToSignUp() {
        this.navigate(SignUp.routeName) {
            launchSingleTop = true
        }
    }

    fun NavHostController.navigateToHome() {
        this.navigate(HomeSend.routeName) {
            launchSingleTop = true
            popUpTo(routeName) {
                inclusive = true
            }
        }
    }


}

fun NavGraphBuilder.routeSignIn(
    router: NavHostController,
    appState: FileBoxState
) {
    composable(SignIn.routeName) {
        val viewModel = hiltViewModel<SignInViewModel>()
        var isLoading by remember {
            mutableStateOf(false)
        }
        val scope = rememberCoroutineScope()

        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions(),
            onResult = { data ->

            }
        )

        val launcherManage = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
            onResult = { data ->

            }
        )
        val ctx = LocalContext.current


        LaunchedEffect(key1 = Unit, block = {
            with(ctx) {
                checkPermissionManageStorage(
                    openPermission = {
                        launcher.launch(
                            permissionReadWrite
                        )
                    },
                    openIntent = {
                        launcherManage.launch(it)
                    }
                )

            }

        })



        ScreenSignIn(
            goToSignUp = { router.navigateToSignUp() },
            onSubmit = { email, password ->
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


