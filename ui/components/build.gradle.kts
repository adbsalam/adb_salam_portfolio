import uk.adbsalam.snapit.plugin.snapIt

apply<ComposableConfig>()

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.paparazzi)
    alias(libs.plugins.snapit)
}

android {
    namespace = asNameSpace("components")
}

snapIt {
    testDir = ui.snapModule
    flavor = DEBUG_FLAVOR
}

dependencies {
    applyProject(ui.theming)

    implementation(libs.app.compat)
    implementation(libs.ktx.core)
    implementation(libs.compose.activity)
    implementation(libs.compose.runtime)
    implementation(libs.compose.material)
    implementation(libs.compose.views.material)
    implementation(libs.compose.foundation)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)

    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.lotti)
}