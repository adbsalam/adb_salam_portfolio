import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)

    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.android.hilt.plugin)
}

android {
    namespace = asNameSpace("network")

    compileSdk = COMPILE_SDK
    defaultConfig {
        minSdk = MIN_SDK
        val apiKey: String =
            project.rootProject
                .file("local.properties")
                .inputStream()
                .use { input ->
                    Properties().apply { load(input) }
                }.getProperty("api_key") ?: ""
        buildConfigField("String", "API_KEY", "\"$apiKey\"")
    }

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

dependencies {
    implementation(libs.okhttp)
    implementation(libs.hilt.android)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.retrofit.converter.gson)
    ksp(libs.hilt.compiler)
    // kspTest(libs.moshi.codegen)
}
