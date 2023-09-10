apply<BaseConfig>()

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.android.hilt.plugin)
    alias(libs.plugins.kotlin.kapt)
    id("kotlin-parcelize")
}

android {
    namespace = asNameSpace("core.prefs")
}

dependencies {
    applyProject(utils.module)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
}