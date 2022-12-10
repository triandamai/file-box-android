object Deps {
    object Com{
        object Google{
            object Dagger{
                val hiltAndroid by lazy{"com.google.dagger:hilt-android:2.44"}
                val hiltAndroidCompiler by lazy{"com.google.dagger:hilt-android-compiler:2.44"}
            }
        }
    }
    object AndroidX{
        object Compose{
            val composeBom  by lazy { "androidx.compose:compose-bom:2022.10.00" }
            val material3 by lazy{"androidx.compose.material3:material3"}
            val ui by lazy { "androidx.compose.ui:ui" }
            val materialIconExtended by lazy{"androidx.compose.material:material-icons-extended"}
            val materialWindowSizeClass by lazy{"androidx.compose.material3:material3-window-size-class"}
            val uiToolingPreview by lazy{"androidx.compose.ui:ui-tooling-preview"}
            val uiTooling by lazy{"androidx.compose.ui:ui-tooling"}
            val uiTestJunit4 by lazy{"androidx.compose.ui:ui-test-junit4"}
            val uiTestManifest by lazy{"androidx.compose.ui:ui-test-manifest"}
        }
        object Work{
            val workRuntime by lazy { "androidx.work:work-runtime-ktx:2.7.1" }
        }
        object Hilt{
            val hiltNavigationCompose by lazy { "androidx.hilt:hilt-navigation-compose:1.0.0" }
            val hiltWork by lazy { "androidx.hilt:hilt-work:1.0.0" }
            val hiltCompiler by lazy{"androidx.hilt:hilt-compiler:1.0.0"}
        }
        object Navigation{
            private const val nav_version = "2.5.3"
            val navigationCompose by lazy {"androidx.navigation:navigation-compose:$nav_version"}
        }
        object Core{
            val coreKtx by lazy { "androidx.core:core-ktx:1.7.0" }
        }
        object Lifecycle{
            val runtimeLifecycleKtx by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1" }
        }
        object Activity{
            val activityCompose by lazy{"androidx.activity:activity-compose:1.3.1"}
        }
        object Test{
            val extJunit by lazy {"androidx.test.ext:junit:1.1.3"}
            val espressoCore by lazy {"androidx.test.espresso:espresso-core:3.4.0"}
        }
        object Multidex{
            private const val multidex_version = "2.0.1"
            val multidex by lazy {"androidx.multidex:multidex:$multidex_version"}
        }
    }
    object Junit{
        val jUnit by lazy{"junit:junit:4.13.2"}
    }
}