package uk.adbsalam.portfolio.startup.feature

sealed class StartupState {
    object OnLoading : StartupState()
    object OnStart : StartupState()
}