apply<DataModuleConfig>()

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
}

android {
    namespace = asNameSpace("startup.data")
}

dependencies {
    testImplementation(libs.junit)
}