package app.trian.filebox.feature.signin

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Environment
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.FileBoxState
import app.trian.filebox.feature.home.Home
import app.trian.filebox.feature.signup.SignUp
import kotlinx.coroutines.launch
import java.security.Permission


object SignIn {
    const val routeName = "sign_in"
}

fun NavHostController.navigateToSignUp() {
    this.navigate(SignUp.routeName) {
        launchSingleTop = true
    }
}

fun NavHostController.navigateToHome() {
    this.navigate(Home.routeName) {
        launchSingleTop = true
        popUpTo(SignIn.routeName) {
            inclusive = true
        }
    }
}


@RequiresApi(Build.VERSION_CODES.R)
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
                Log.e("sa", data.toString())
                val isGranted1 = data[Manifest.permission.READ_EXTERNAL_STORAGE]!!
                val isGranted2 = data[Manifest.permission.READ_EXTERNAL_STORAGE]!!
                if (isGranted1 && isGranted2) {
                    viewModel.readFile()
                }
            }
        )

        val launcherManage = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
            onResult = {
                data->
                Log.e("sa", data.toString())
                viewModel.readFile()
            }
        )
        val ctx = LocalContext.current
        fun checkPermission(): Boolean {
            return if(SDK_INT >= Build.VERSION_CODES.R){
                Environment.isExternalStorageManager()
            }else{
                val read = (ContextCompat.checkSelfPermission(ctx,Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                val write = ContextCompat.checkSelfPermission(ctx,Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

                return (read && write)
            }
        }

        LaunchedEffect(key1 = Unit, block = {
            Log.e("sa", checkPermission().toString())
            if (!checkPermission()) {
                if(SDK_INT >= Build.VERSION_CODES.R) {
                    scope.launch {
                        try {
                            Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION).apply {
                                addCategory("android.intent.category.DEFAULT")
                                data = Uri.parse(String.format("package:%s", ctx.packageName))

                                launcherManage.launch(this)
                            }

                        } catch (e: Exception) {
                            Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION).apply {
                                launcherManage.launch(this)
                            }
                        }
                    }
                }else {
                    scope.launch {
                        launcher.launch(
                            arrayOf(
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                            )
                        )
                    }
                }
            }else{
                viewModel.readFile()
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