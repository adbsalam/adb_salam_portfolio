package uk.adbsalam.portfolio.home.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uk.adbsalam.portfolio.data.HomeRepo
import uk.adbsalam.portfolio.data.objects.HomeItems
import uk.adbsalam.portfolio.network.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepo: HomeRepo
) : ViewModel() {

    private val _viewState =
        MutableStateFlow<HomeScreenState>(HomeScreenState.OnLoading)
    internal val viewState = _viewState.asStateFlow()

    internal suspend fun initHome() {
        viewModelScope.launch {

            when (val result = homeRepo.homeItems()) {
                is Response.Failure -> {
                    //TODO ERROR VIEW
                }

                is Response.Success -> {
                    val items = mapToHomeScreenItems(result.data)
                    _viewState.value = HomeScreenState.OnHome(items)
                }
            }
        }
    }

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