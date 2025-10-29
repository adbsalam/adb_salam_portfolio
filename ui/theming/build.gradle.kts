

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = asNameSpace("theming")

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
    applyProject(utils.module)
    applyProject(core.prefs)

    implementation(libs.app.compat)
    implementation(libs.compose.runtime)
    implementation(libs.compose.material)
    implementation(libs.compose.foundation)
    implementation(libs.compose.system.ui)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.ui.tooling)
}
