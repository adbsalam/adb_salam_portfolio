package uk.adbsalam.portfolio.home.feature

sealed class HomeScreenState {
    object OnLoading: HomeScreenState()
    object OnHome: HomeScreenState()
}