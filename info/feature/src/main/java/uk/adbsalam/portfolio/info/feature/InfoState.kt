package uk.adbsalam.portfolio.info.feature

internal sealed interface InfoScreenState{
    object OnLoading: InfoScreenState
    object OnInfo: InfoScreenState
}