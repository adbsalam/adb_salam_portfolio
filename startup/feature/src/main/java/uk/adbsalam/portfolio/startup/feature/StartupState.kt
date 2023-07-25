package uk.adbsalam.portfolio.startup.feature

sealed class StartupState {
    object OnLoading : StartupState()
    data class OnStart(val isFirstTime: String) : StartupState()
}