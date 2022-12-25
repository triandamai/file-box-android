/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.49
 */
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
import app.trian.filebox.base.BaseSnackBar
import app.trian.filebox.base.BaseTopAppBar
import app.trian.filebox.base.extensions.listenChanges
import app.trian.filebox.base.rememberFileBoxApplication
import app.trian.filebox.feature.signin.SignIn
import app.trian.filebox.worker.SyncDeviceWorker
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
                    SnackbarHost(
                        hostState = appState.snackbarHostState,
                        snackbar = {
                            BaseSnackBar(appState = appState, router = router, data = it)
                        }
                    )
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
    }

    override fun onStart() {
        super.onStart()
        syncDevice()
    }

    private fun syncDevice(){
        val worker = OneTimeWorkRequestBuilder<SyncDeviceWorker>()
            .build()

        WorkManager.getInstance(this)
            .beginWith(
                listOf(worker)
            )
            .enqueue()
    }


}

