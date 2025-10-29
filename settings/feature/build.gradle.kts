import uk.adbsalam.snapit.plugin.snapIt



plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.android.hilt.plugin)
    alias(libs.plugins.paparazzi)
    alias(libs.plugins.snapit)
}

android {
    namespace = asNameSpace("settings.feature")

    compileSdk = COMPILE_SDK
    defaultConfig {
        minSdk = MIN_SDK
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

snapIt {
    testDir = settings.snapFeature
    flavor = DEBUG_FLAVOR
}

dependencies {
    applyProject(ui.components)
    applyProject(ui.theming)
    applyProject(utils.module)
    applyProject(core.prefs)

    implementation(libs.app.compat)
    implementation(libs.compose.runtime)
    implementation(libs.compose.material)
    implementation(libs.compose.foundation)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.ui.tooling)
    implementation(libs.hilt.navigation)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}
