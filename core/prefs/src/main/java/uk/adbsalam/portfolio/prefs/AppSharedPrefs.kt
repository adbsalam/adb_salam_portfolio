package uk.adbsalam.portfolio.prefs

import uk.adbsalam.portfolio.utils.Theme
import javax.inject.Inject

sealed class AppSharedPrefManager {
    class ThemePrefs @Inject constructor(
        private val pref: PrefManager
    ) : AppSharedPrefManager() {

        fun setTheme(theme: Theme) = pref.saveString("theme", theme.title)
        fun theme(): Theme {
            val theme = pref.loadString("theme", Theme.SYSTEM.title)
            return Theme.values().first { it.title == theme }
        }
    }
}