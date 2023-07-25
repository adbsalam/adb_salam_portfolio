package uk.adbsalam.portfolio.startup.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uk.adbsalam.portfolio.prefs.AppSharedPrefManager
import javax.inject.Inject

@HiltViewModel
class StartupViewModel @Inject constructor(
    private val prefs: AppSharedPrefManager.ThemePrefs,
    private val userPrefs: AppSharedPrefManager.ThemePrefs
): ViewModel() {

    private val _viewState = MutableStateFlow<StartupState>(StartupState.OnLoading)
    val viewState = _viewState.asStateFlow()

    suspend fun loadApp(){
        viewModelScope.launch {

        }
    }

}