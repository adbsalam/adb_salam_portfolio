package uk.adbsalam.portfolio.startup.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uk.adbsalam.portfolio.prefs.AppSharedPrefManager
import uk.adbsalam.portfolio.utils.Theme
import javax.inject.Inject

@HiltViewModel
class StartupViewModel @Inject constructor(
    private val prefs: AppSharedPrefManager.ThemePrefs,
    private val userPrefs: AppSharedPrefManager.ThemePrefs
) : ViewModel() {

    private val _theme = MutableStateFlow(Theme.SYSTEM)
    val currentTheme = _theme.asStateFlow()

    private val _dynamic = MutableStateFlow(false)
    val dynamic = _dynamic.asStateFlow()

    private val _uiState = MutableStateFlow<StartupState>(StartupState.OnLoading)
    val uiState = _uiState.asStateFlow()


    suspend fun initApp() {
        viewModelScope.launch {
            delay(2000)
            _uiState.value = StartupState.OnStart
        }
    }

    fun onDark() {
        viewModelScope.launch {
            _theme.value = Theme.DARK
        }
    }


    fun onLight() {
        viewModelScope.launch {
            _theme.value = Theme.LIGHT
        }
    }


    fun onSystem() {
        viewModelScope.launch {
            _theme.value = Theme.SYSTEM
        }
    }

    fun onDynamic() {
        viewModelScope.launch {
            _dynamic.value = !_dynamic.value
        }
    }


}