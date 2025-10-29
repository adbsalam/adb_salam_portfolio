import uk.adbsalam.snapit.plugin.snapIt



plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.android.hilt.plugin)
    alias(libs.plugins.paparazzi)
    alias(libs.plugins.snapit)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = asNameSpace("walkie.feature")

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
    testDir = walkie.snapFeature
    flavor = DEBUG_FLAVOR
}

dependencies {
    applyProject(ui.components)
    applyProject(ui.theming)
    applyProject(utils.module)
    applyProject(core.prefs)
    applyProject(core.communication)

    implementation(libs.app.compat)
    implementation(libs.compose.activity)
    implementation(libs.compose.runtime)
    implementation(libs.compose.material)
    implementation(libs.compose.foundation)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.lotti)
    implementation(libs.compose.ui.tooling)
    implementation(libs.hilt.navigation)
    implementation(libs.play.services.nearby)

    ksp(libs.hilt.compiler)
    implementation(libs.hilt.android)
}
