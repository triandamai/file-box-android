/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.46
 */
package app.trian.filebox.feature.homeHistory

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.base.EventListener
import app.trian.filebox.base.FileBoxState


object HomeHistory {
    const val routeName = "home_history"
}

fun NavGraphBuilder.routeHomeHistory(
    router: NavHostController,
    appState: FileBoxState,
    event: EventListener
) {
    composable(HomeHistory.routeName) {
        val viewModel = hiltViewModel<HomeHistoryViewModel>()
        ScreenHomeHistory()
    }
}
