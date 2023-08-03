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

        fun setDynamic(value: Boolean) = pref.saveBoolean("dynamic", value)
        fun dynamicColors() = pref.loadBoolean("dynamic", false)

        fun setChristmas(value: Boolean) = pref.saveBoolean("christmas", value)
        fun christmas() = pref.loadBoolean("christmas", false)
    }
}