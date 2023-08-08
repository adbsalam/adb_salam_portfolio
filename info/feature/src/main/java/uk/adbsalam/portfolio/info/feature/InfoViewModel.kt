package uk.adbsalam.portfolio.info.feature

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor() : ViewModel() {

    private val _viewState = MutableStateFlow<InfoScreenState>(InfoScreenState.OnLoading)
    internal val viewState = _viewState.asStateFlow()

    /**
     * Init info details
     * This will change state from loading to OnInfo
     */
    internal suspend fun initInfo() {
        _viewState.value = InfoScreenState.OnInfo
    }
}