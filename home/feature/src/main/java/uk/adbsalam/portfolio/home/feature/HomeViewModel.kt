package uk.adbsalam.portfolio.home.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uk.adbsalam.portfolio.data.HomeRepo
import uk.adbsalam.portfolio.data.objects.HomeItems
import uk.adbsalam.portfolio.home.feature.utils.HomeItemType
import uk.adbsalam.portfolio.home.feature.utils.HomeScreenItem
import uk.adbsalam.portfolio.network.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepo: HomeRepo
) : ViewModel() {

    private val _viewState =
        MutableStateFlow<HomeScreenState>(HomeScreenState.OnLoading)
    internal val viewState = _viewState.asStateFlow()

    /**
     * init home items setup
     */
    internal suspend fun initHome() {
        loadHomeItems()
    }

    /**
     * Load home items
     * Set state according to data loaded or error states
     */
    internal fun loadHomeItems() {
        _viewState.value = HomeScreenState.OnLoading

        viewModelScope.launch {

            when (val result = homeRepo.homeItems()) {
                is Response.Failure -> {
                    _viewState.value =
                        HomeScreenState.OnError("something went wrong, please try again")
                }

                is Response.Success -> {
                    val items = mapToHomeScreenItems(result.data)
                    _viewState.value = HomeScreenState.OnHome(items)
                }
            }
        }
    }

    /**
     * Map HomeItems to Home Screen UI Item
     * This will map icons and items to be ready for UI use
     */
    private fun mapToHomeScreenItems(items: HomeItems): List<HomeScreenItem> {
        return items.home.map { item ->
            HomeScreenItem(
                tags = item.tags,
                title = item.title,
                type = HomeItemType.values().first { enum -> item.type == enum.type },
                res = item.res,
                body = item.body,
                deeplink = item.deeplink
            )
        }
    }
}