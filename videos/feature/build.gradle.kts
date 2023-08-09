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

android {
    namespace = "uk.adbsalam.portfolio.videos.feature"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
    }

    @Suppress("UnstableApiUsage")
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

snapIt {
    testDir = "src/test/java/uk/adbsalam/portfolio/videos/feature"
    flavor = "debug"
}

dependencies {
    implementation(project(":ui:components"))
    implementation(project(":ui:theming"))
    implementation(project(":videos:data"))
    implementation(project(":utils"))
    implementation(project(":core:prefs"))
    implementation(project(":core:network"))

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
    kapt(libs.hilt.compiler)
}