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
    namespace = asNameSpace("blog.feature")
}

snapIt {
    testDir = blog.snapFeature
    flavor = DEBUG_FLAVOR
}

dependencies {
    applyProject(ui.components)
    applyProject(ui.theming)
    applyProject(utils.module)
    applyProject(navigation.module)
    applyProject(blog.data)
    applyProject(core.prefs)
    applyProject(core.network)
    applyProject(samples.feature)

    implementation(libs.app.compat)
    implementation(libs.ktx.core)
    implementation(libs.compose.lotti)
    implementation(libs.compose.runtime)
    implementation(libs.compose.material)
    implementation(libs.compose.foundation)
    implementation(libs.compose.ui)
    implementation(libs.compose.views.material)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.hilt.navigation)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}