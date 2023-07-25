import uk.adbsalam.snapit.plugin.snapIt

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.paparazzi)
    alias(libs.plugins.snapit)
}

android {
    namespace = "uk.adbsalam.portfolio.home.feature"
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
    testDir = "src/test/java/uk/adbsalam/portfolio/home/feature"
    flavor = "debug"
}

dependencies {
    implementation(project(":ui:components"))
    implementation(project(":utils"))

    implementation(libs.app.compat)
    implementation(libs.compose.runtime)
    implementation(libs.compose.material)
    implementation(libs.compose.foundation)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.ui.tooling)
}