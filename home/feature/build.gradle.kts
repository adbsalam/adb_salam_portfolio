import uk.adbsalam.snapit.plugin.snapIt

apply<ComposableConfig>()

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.paparazzi)
    alias(libs.plugins.snapit)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.android.hilt.plugin)
}

android {
    namespace = asNameSpace("home.feature")
}

snapIt {
    testDir = home.snapFeature
    flavor = DEBUG_FLAVOR
}

dependencies {
    applyProject(ui.components)
    applyProject(ui.theming)
    applyProject(core.prefs)
    applyProject(core.network)
    applyProject(settings.feature)
    applyProject(home.data)
    applyProject(walkie.feature)
    applyProject(utils.module)
    applyProject(navigation.module)

    implementation(libs.compose.lotti)
    implementation(libs.app.compat)
    implementation(libs.compose.runtime)
    implementation(libs.compose.material)
    implementation(libs.compose.foundation)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.hilt.navigation)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}