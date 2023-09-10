import uk.adbsalam.snapit.plugin.snapIt

apply<ComposableConfig>()

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.android.hilt.plugin)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.paparazzi)
    alias(libs.plugins.snapit)
}

android {
    namespace = asNameSpace("startup.feature")
}

snapIt {
    testDir = startup.snapFeature
    flavor = DEBUG_FLAVOR
}

dependencies {
    applyProject(ui.components)
    applyProject(ui.theming)
    applyProject(utils.module)
    applyProject(core.prefs)
    applyProject(navHost.feature)

    implementation(libs.app.compat)
    implementation(libs.compose.activity)
    implementation(libs.compose.runtime)
    implementation(libs.compose.material)
    implementation(libs.compose.foundation)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.ui.tooling)
    implementation(libs.hilt.navigation)

    kapt(libs.hilt.compiler)
    implementation(libs.hilt.android)
}