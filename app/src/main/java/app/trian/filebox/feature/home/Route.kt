package app.trian.filebox.feature.home

import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import app.trian.filebox.FileBoxState
import app.trian.filebox.composables.BottomBarListener
import app.trian.filebox.feature.signin.SignIn
import kotlinx.coroutines.launch


object Home {
    const val routeName = "home"
}


fun NavGraphBuilder.routeHome(
    router: NavHostController,
    appState: FileBoxState
) {
    composable(
        Home.routeName,
    ) {
        val viewModel = hiltViewModel<HomeViewModel>()
        val scope = rememberCoroutineScope()
        appState.addBottomBarListener(object : BottomBarListener<String> {
            override fun onItemClicked(data: String) {

            }

        })

        ScreenHome()
    }
}