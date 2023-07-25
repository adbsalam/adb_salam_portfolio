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
internal class StartupViewModel @Inject constructor(
    private val prefs: AppSharedPrefManager.ThemePrefs,
) : ViewModel() {

    private val _theme = MutableStateFlow(prefs.theme())
    internal val currentTheme = _theme.asStateFlow()

    private val _dynamic = MutableStateFlow(prefs.dynamicColors())
    internal val dynamic = _dynamic.asStateFlow()

    private val _uiState = MutableStateFlow<StartupState>(StartupState.OnLoading)
    internal val uiState = _uiState.asStateFlow()

    internal suspend fun initApp() {
        viewModelScope.launch {
            delay(2000)
            _uiState.value = StartupState.OnStart
        }
    }

    internal fun onThemeChange(theme: Theme) {
        viewModelScope.launch {
            prefs.setTheme(theme)
            _theme.value = theme
        }
    }


    internal fun onDynamic(dynamic: Boolean) {
        viewModelScope.launch {
            prefs.setDynamic(dynamic)
            _dynamic.value = dynamic
        }
    }
}