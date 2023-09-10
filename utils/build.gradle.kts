plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
}

apply<DataModuleConfig>()

android {
    namespace = "uk.adbsalam.portfolio.utils"
}

dependencies {}