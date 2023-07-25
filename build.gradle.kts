buildscript {}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.kotlin) apply false
    alias(libs.plugins.android.hilt.plugin) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.paparazzi) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.kotlin.ksp) apply false
    alias(libs.plugins.snapit) apply false
}

subprojects {
    pluginManager.withPlugin("com.android.library") {
        val implementation by configurations
        dependencies {
            implementation(platform(libs.compose.bom))
            implementation(libs.compose.runtime)
        }
    }
}