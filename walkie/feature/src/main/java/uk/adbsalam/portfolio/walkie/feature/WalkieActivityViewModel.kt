package uk.adbsalam.portfolio.walkie.feature

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import uk.adbsalam.portfolio.prefs.AppSharedPrefManager
import javax.inject.Inject

@HiltViewModel
class WalkieActivityViewModel @Inject constructor(
    private val prefs: AppSharedPrefManager.ThemePrefs,
) : ViewModel() {

    private val _viewState = MutableStateFlow<WalkieState>(WalkieState.OnLoading)
    internal val viewState = _viewState.asStateFlow()

    fun onStartConnection() {
        _viewState.value = WalkieState.OnConnect
    }

    fun onPermissionsError() {
        _viewState.value = WalkieState.OnPermissionsError
    }

    fun onRequirePermission() {
        _viewState.value = WalkieState.OnRequirePermissions
    }

    val currentTheme
        get() = prefs.theme()

    val isDynamic
        get() = prefs.dynamicColors()
}