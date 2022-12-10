// Top-level build file where you can add configuration options common to all sub-projects/modules.
extensions.findByName("buildScan")?.withGroovyBuilder {
    setProperty("termsOfServiceUrl", "https://gradle.com/terms-of-service")
    setProperty("termsOfServiceAgree", "yes")
}
plugins {
    id("com.android.application") version "7.3.1" apply false
    id("com.android.library") version "7.3.1" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    kotlin("android") version "1.6.10" apply false
}
tasks.create<Delete>("cleanRp"){
    delete(
            rootProject.buildDir
    )
}