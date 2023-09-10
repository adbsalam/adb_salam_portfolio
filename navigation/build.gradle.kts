apply<FeatureModuleConfig>()

plugins {
   alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    id("kotlin-parcelize")
}

android {
    namespace = asNameSpace("navigation")
}

dependencies {
    implementation(libs.compose.ui)
    implementation(libs.gson)
    implementation(libs.compose.nav)
}