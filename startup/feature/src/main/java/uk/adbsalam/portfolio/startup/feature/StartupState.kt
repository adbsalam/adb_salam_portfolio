package uk.adbsalam.portfolio.startup.feature

internal sealed class StartupState {
    object OnLoading : StartupState()
    object OnStart : StartupState()
}