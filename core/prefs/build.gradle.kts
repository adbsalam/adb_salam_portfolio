plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.android.hilt.plugin)
    alias(libs.plugins.kotlin.kapt)
    id("kotlin-parcelize")
}

apply<DataModuleConfig>()

android {
    namespace = "uk.adbsalam.portfolio.core.prefs"
}

dependencies {
    implementation(project(":utils"))
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
}