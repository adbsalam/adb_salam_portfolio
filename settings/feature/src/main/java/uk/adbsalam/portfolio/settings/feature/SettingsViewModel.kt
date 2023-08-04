package uk.adbsalam.portfolio.settings.feature

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uk.adbsalam.portfolio.prefs.AppSharedPrefManager
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val themePrefs: AppSharedPrefManager.ThemePrefs
): ViewModel() {

    fun theme() = themePrefs.theme()

    fun isDynamicColors() = themePrefs.dynamicColors()
}