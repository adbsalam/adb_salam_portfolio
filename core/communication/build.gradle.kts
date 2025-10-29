
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)

    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.android.hilt.plugin)
}

android {
    namespace = asNameSpace("communication")

    compileSdk = COMPILE_SDK
    defaultConfig {
        minSdk = MIN_SDK
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

dependencies {
    implementation(libs.play.services.nearby)

    ksp(libs.hilt.compiler)
    implementation(libs.hilt.android)
}
