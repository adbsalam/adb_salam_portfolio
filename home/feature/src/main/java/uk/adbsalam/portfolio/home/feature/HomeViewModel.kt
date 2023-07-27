package uk.adbsalam.portfolio.home.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uk.adbsalam.portfolio.prefs.AppSharedPrefManager
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _viewState =
        MutableStateFlow<HomeScreenState>(HomeScreenState.OnLoading)
    internal val viewState = _viewState.asStateFlow()

    internal suspend fun initHome() {
        viewModelScope.launch {
            _viewState.value = HomeScreenState.OnHome
        }
    }
}