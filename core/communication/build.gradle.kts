apply<BaseConfig>()

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.android.hilt.plugin)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = asNameSpace("communication")
}

dependencies {
    implementation(libs.play.services.nearby)

    kapt(libs.hilt.compiler)
    implementation(libs.hilt.android)
}