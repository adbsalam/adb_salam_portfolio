
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)

    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.android.hilt.plugin)
}

android {
    namespace = asNameSpace("info.data")

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
    applyProject(core.network)

    implementation(libs.okhttp)
    implementation(libs.hilt.android)
    implementation(libs.retrofit)
    implementation(libs.moshi)
    ksp(libs.moshi.codegen)
    ksp(libs.hilt.compiler)
}
