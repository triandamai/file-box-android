package app.trian.filebox.base.extensions

import android.content.Context
import android.content.res.Configuration
import androidx.navigation.NavHostController
import app.trian.filebox.base.BottomBarType
import app.trian.filebox.base.FileBoxState
import app.trian.filebox.base.TopAppBarType
import app.trian.filebox.feature.homeHistory.HomeHistory
import app.trian.filebox.feature.homeReceive.HomeReceive
import app.trian.filebox.feature.homeSend.HomeSend
import app.trian.filebox.feature.showSelectedFile.ShowSelectedFile
import app.trian.filebox.feature.signin.SignIn
import app.trian.filebox.feature.signup.SignUp

fun NavHostController.listenChanges(appState: FileBoxState, ctx: Context, config: Configuration) {
    this.addOnDestinationChangedListener { _, destination, _ ->
        with(appState) {
            setCurrentRoute(destination.route.orEmpty())
            when (destination.route) {
                in listOf(
                    HomeSend.routeName,
                    HomeReceive.routeName,
                    HomeHistory.routeName
                ) -> {
                    if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        showNavRail()
                        hideBottomBar()
                    } else {
                        hideNavRail()
                        showBottomBar(BottomBarType.BASIC)
                    }
                    showAppbar(TopAppBarType.BASIC)
                }
                in listOf(
                    SignIn.routeName,
                    SignUp.routeName
                ) -> {
                    hideNavRail()
                    hideBottomBar()
                    hideAppbar()
                }
                in listOf(ShowSelectedFile.routeName) ->{
                    showAppbar(TopAppBarType.SHOW_SELECTED_FILE)
                }
                else -> {
                    hideNavRail()
                    hideBottomBar()
                    hideAppbar()
                }
            }
        }
    }
}