import uk.adbsalam.snapit.plugin.snapIt



plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.paparazzi)
    alias(libs.plugins.snapit)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.android.hilt.plugin)
}

android {
    namespace = asNameSpace("reviews.feature")

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
    testDir = reviews.snapFeature
    flavor = DEBUG_FLAVOR
}

dependencies {
    applyProject(ui.components)
    applyProject(ui.theming)
    applyProject(utils.module)
    applyProject(reviews.data)
    applyProject(core.network)
    applyProject(core.prefs)

    implementation(libs.compose.runtime)
    implementation(libs.compose.material)
    implementation(libs.compose.foundation)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.app.compat)
    implementation(libs.ktx.core)
    implementation(libs.youtube.player)
    implementation(libs.youtube.player.seekbar)
    implementation(libs.hilt.navigation)
    implementation(libs.hilt.android)
    implementation(libs.compose.lotti)
    ksp(libs.hilt.compiler)
}
