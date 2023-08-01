package uk.adbsalam.portfolio.info.feature

sealed interface InfoScreenState{
    object OnLoading: InfoScreenState
    object OnInfo: InfoScreenState
}