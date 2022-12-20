package app.trian.filebox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import app.trian.filebox.base.BaseBottomBar
import app.trian.filebox.base.BaseContainer
import app.trian.filebox.base.BaseTopAppBar
import app.trian.filebox.base.listenChanges
import app.trian.filebox.base.rememberFileBoxApplication
import app.trian.filebox.feature.signin.SignIn
import app.trian.filebox.worker.AudiosSyncWorker
import app.trian.filebox.worker.DocumentsSyncWorker
import app.trian.filebox.worker.ImagesSyncWorker
import app.trian.filebox.worker.VideosSyncWorker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val router = rememberNavController()
            val appState = rememberFileBoxApplication()
            val ctx = LocalContext.current
            val config = LocalConfiguration.current

            LaunchedEffect(key1 = router, block = {
                router.listenChanges(appState, ctx, config)
            })
            BaseContainer(
                topBar = {
                    BaseTopAppBar(appState = appState, router = router)
                },
                bottomBar = {
                    BaseBottomBar(
                        appState = appState,
                        router = router
                    )
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

