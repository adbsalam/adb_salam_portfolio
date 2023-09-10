import uk.adbsalam.snapit.plugin.snapIt

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.paparazzi)
    alias(libs.plugins.snapit)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.android.hilt.plugin)
}

apply<FeatureModuleConfig>()

android {
    namespace = "uk.adbsalam.portfolio.info.feature"
}

snapIt {
    testDir = "src/test/java/uk/adbsalam/portfolio/info/feature"
    flavor = "debug"
}

dependencies {
    applyProject(ui.components)
    applyProject(ui.theming)
    applyProject(utils.module)
    applyProject(settings.feature)
    applyProject(info.data)
    applyProject(core.network)

    implementation(libs.compose.lotti)
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