/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.46
 */
package app.trian.filebox.feature.blank

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import app.trian.filebox.base.FileBoxState


object Blank {
    const val routeName = "blank"


}

fun NavGraphBuilder.routeBlank(
    router: NavHostController,
    appState: FileBoxState
) {
    composable(Blank.routeName) {
        val viewModel = hiltViewModel<BlankViewModel>()
        ScreenBlank()
    }
}
