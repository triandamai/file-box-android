package app.trian.filebox.feature.splash

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.feature.homeSend.HomeSend
import app.trian.filebox.feature.signin.SignIn
import app.trian.filebox.feature.signup.SignUp
import app.trian.filebox.feature.splash.Splash.navigateToHome
import app.trian.filebox.feature.splash.Splash.navigateToSignIn

object Splash {
    const val routeName = "Splash"

    fun NavHostController.navigateToHome(){
        navigate(HomeSend.routeName){
            popUpTo(routeName){
                inclusive = true
            }
            launchSingleTop=true
        }
    }

    fun NavHostController.navigateToSignIn(){
        navigate(SignIn.routeName){
            popUpTo(routeName){
                inclusive = true
            }
            launchSingleTop=true
        }
    }
}

fun NavGraphBuilder.routeSplash(
    router: NavHostController,
) {
    composable(Splash.routeName) {
        val viewModel = hiltViewModel<SplashViewModel>()
        LaunchedEffect(viewModel){
            viewModel.isLoggedIn {
                with(router) {
                    if (it) {
                        navigateToHome()
                    } else {
                        navigateToSignIn()
                    }
                }
            }
        }
        ScreenSplash()
    }
}