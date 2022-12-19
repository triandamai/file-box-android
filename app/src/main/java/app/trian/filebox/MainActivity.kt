package app.trian.filebox

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.compose.rememberNavController
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import app.trian.filebox.components.BottomBarSelectFile
import app.trian.filebox.components.FileBoxBottomNavigation
import app.trian.filebox.feature.homeHistory.HomeHistory
import app.trian.filebox.feature.homeReceive.HomeReceive
import app.trian.filebox.feature.homeSend.HomeSend
import app.trian.filebox.feature.signin.SignIn
import app.trian.filebox.feature.signup.SignUp
import app.trian.filebox.worker.AudiosSyncWorker
import app.trian.filebox.worker.DocumentsSyncWorker
import app.trian.filebox.worker.ImagesSyncWorker
import app.trian.filebox.worker.VideosSyncWorker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val router = rememberNavController()
            val appState = rememberFileBoxApplication()
            val config = LocalConfiguration.current

            LaunchedEffect(key1 = router, block = {
                router.addOnDestinationChangedListener { _, destination, _ ->
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
                                    changeBottomBar(BottomBarType.BASIC)
                                }
                                showAppbar()
                            }
                            in listOf(
                                SignIn.routeName,
                                SignUp.routeName
                            ) -> {
                                hideNavRail()
                                hideBottomBar()
                                hideAppbar()
                            }
                            else -> {
                                hideNavRail()
                                hideBottomBar()
                                hideAppbar()
                            }
                        }
                    }
                }
            })
            BaseContainer(
                topBar = {
                    if (appState.showAppbar) {
                        TopAppBar(
                            title = {
                                Text(text = "File Box")
                            }
                        )
                    }
                },
                bottomBar = {
                    when (appState.bottomBarType) {
                        BottomBarType.BASIC -> {

                            FileBoxBottomNavigation(
                                items = listOf(
                                    FileBoxBottomNavigation.Send(),
                                    FileBoxBottomNavigation.Receive(),
                                    FileBoxBottomNavigation.History()
                                ),
                                currentRoute = appState.activeRoute,
                                onItemClick = {
                                    router.navigate(it) {
                                        launchSingleTop = true
                                    }
                                    appState.setCurrentRoute(it)
                                }
                            )
                        }
                        BottomBarType.PICK_FILE -> {

                            BottomBarSelectFile(
                                message = "${appState.selectedFileCount} File Selected",
                                onDetailClicked = {},
                                onShareClicked = {}
                            )
                        }
                        else -> {}
                    }
                },
                snackbarHost = {
                    SnackbarHost(hostState = appState.snackbarHostState)
                },
                appState = appState,
                router = router
            ) {
                AppNavigation(
                    navController = router,
                    appState = appState,
                    startDestination = SignIn.routeName,
                )
            }
        }

        startSync()
    }


    private fun startSync() {
        val images = OneTimeWorkRequestBuilder<ImagesSyncWorker>()
            .build()
        val videos = OneTimeWorkRequestBuilder<VideosSyncWorker>()
            .build()
        val audio = OneTimeWorkRequestBuilder<AudiosSyncWorker>()
            .build()
        val document = OneTimeWorkRequestBuilder<DocumentsSyncWorker>()
            .build()

        WorkManager.getInstance(this)
            .beginWith(
                listOf(
                    images,
                    videos,
                    audio,
                    document
                )
            )
            .enqueue()
    }


}

