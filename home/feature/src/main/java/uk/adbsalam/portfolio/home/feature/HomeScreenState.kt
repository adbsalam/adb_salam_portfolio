package uk.adbsalam.portfolio.home.feature

internal sealed class HomeScreenState {
    object OnLoading: HomeScreenState()
    data class OnHome(val homeItems: List<HomeScreenItem>): HomeScreenState()
}