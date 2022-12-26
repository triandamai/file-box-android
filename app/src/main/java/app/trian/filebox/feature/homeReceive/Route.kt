/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.46
 */
package app.trian.filebox.feature.homeReceive

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.base.EventListener
import app.trian.filebox.base.FileBoxState
import app.trian.filebox.data.models.FileModel


object HomeReceive {
    const val routeName = "home_receive"
}

fun NavGraphBuilder.routeHomeReceive(
    router: NavHostController,
    appState: FileBoxState,
    event: EventListener
) {
    composable(HomeReceive.routeName) {
        val viewModel = hiltViewModel<HomeReceiveViewModel>()
        val allFiles = mapOf<String, List<FileModel>>()
        ScreenHomeReceive()
    }
}
