plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.android.hilt.plugin)
    alias(libs.plugins.kotlin.ksp)
}

apply<DataModuleConfig>()

android {
    namespace = "uk.adbsalam.portfolio.reviews.data"
}

dependencies {
    implementation(project(":core:network"))
    implementation(libs.okhttp)
    implementation(libs.hilt.android)
    implementation(libs.retrofit)
    implementation(libs.moshi)
    ksp(libs.moshi.codegen)

    kapt(libs.hilt.compiler)
}