plugins {
    id("com.android.application")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace= "app.trian.filebox"
    compileSdk =33

    defaultConfig {
        applicationId ="app.trian.filebox"
        minSdk= 21
        targetSdk= 33
        versionCode= 1
        versionName ="1.0"
        multiDexEnabled = true

        testInstrumentationRunner= "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary =true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled=false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

        }
    }
    compileOptions {
        sourceCompatibility =JavaVersion.VERSION_1_8
        targetCompatibility =JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose= true
    }
    composeOptions {
        kotlinCompilerExtensionVersion= GlobalVersion.composeVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(Deps.AndroidX.Core.coreKtx)
    implementation(Deps.AndroidX.Lifecycle.runtimeLifecycleKtx)
    implementation(Deps.AndroidX.Activity.activityCompose)
    implementation(Deps.AndroidX.Multidex.multidex)
    with(Deps.AndroidX.Compose){
        implementation(platform(composeBom))
        androidTestImplementation(platform(composeBom))
        implementation(material3)
        implementation(ui)
        implementation(uiToolingPreview)
        debugImplementation(uiTooling)
        androidTestImplementation(uiTestJunit4)
        debugImplementation(uiTestManifest)
        implementation(materialIconExtended)
        implementation(materialWindowSizeClass)
    }
    with(Deps.Com.Google.Dagger){
        implementation(hiltAndroid)
        kapt(hiltAndroidCompiler)
    }
    implementation(Deps.AndroidX.Hilt.hiltNavigationCompose)
    implementation(Deps.AndroidX.Navigation.navigationCompose)
    implementation(Deps.AndroidX.Work.workRuntime)
    implementation(Deps.AndroidX.Hilt.hiltWork)
    kapt(Deps.AndroidX.Hilt.hiltCompiler)
    with(Deps.Com.Google.Firebase){
        implementation(platform(firebaseBom))
        implementation(firebaseAnalytics)
        implementation(firebaseAuth)
        implementation(firebaseFirestore)
        implementation(firebaseStorage)
    }
    implementation(Deps.Org.Jetbrains.Kotlinx.kotlinxCoroutineAndroid)

    testImplementation(Deps.Junit.jUnit)
    androidTestImplementation(Deps.AndroidX.Test.extJunit)
    androidTestImplementation(Deps.AndroidX.Test.espressoCore)
}
// Allow references to generated code
kapt {
    correctErrorTypes = true
}