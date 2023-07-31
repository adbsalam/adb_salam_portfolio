package uk.adbsalam.portfolio.home.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uk.adbsalam.portfolio.data.HomeRepo
import uk.adbsalam.portfolio.network.Response
import uk.adbsalam.portfolio.prefs.AppSharedPrefManager
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

            val res = homeRepo.homeItems()

            if(res is Response.Success){
                res.data.home.forEach {
                    println("-----------------------------------" + it.title)
                }
            }

            _viewState.value = HomeScreenState.OnHome
        }
    }
}