plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.android.hilt.plugin)
    alias(libs.plugins.kotlin.ksp)
}

apply<DataModuleConfig>()

android {
    namespace = "uk.adbsalam.portfolio.network"
}

dependencies {
    implementation(libs.okhttp)
    implementation(libs.hilt.android)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.retrofit.converter.gson)
    kapt(libs.hilt.compiler)
    kspTest(libs.moshi.codegen)
}