import uk.adbsalam.snapit.plugin.snapIt

apply<ComposableConfig>()

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.android.hilt.plugin)
    alias(libs.plugins.paparazzi)
    alias(libs.plugins.snapit)
}

android {
    namespace = asNameSpace("samples.feature")
}

snapIt {
    testDir = samples.snapModule
    flavor = DEBUG_FLAVOR
}

dependencies {
    applyProject(ui.theming)
    applyProject(ui.components)

    implementation(libs.app.compat)
    implementation(libs.ktx.core)
    implementation(libs.compose.activity)
    implementation(libs.compose.runtime)
    implementation(libs.compose.material)
    implementation(libs.compose.views.material)
    implementation(libs.compose.foundation)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.hilt.navigation)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.lotti)
}