package uk.adbsalam.portfolio.home.feature

import uk.adbsalam.portfolio.home.feature.utils.HomeScreenItem

internal sealed class HomeScreenState {
    object OnLoading: HomeScreenState()
    data class OnHome(val homeItems: List<HomeScreenItem>): HomeScreenState()
    data class OnError(val errorMessage: String): HomeScreenState()
}