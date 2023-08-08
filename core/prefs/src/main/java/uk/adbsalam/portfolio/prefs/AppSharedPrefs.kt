package uk.adbsalam.portfolio.prefs

import uk.adbsalam.portfolio.utils.Theme
import javax.inject.Inject

sealed class AppSharedPrefManager {
    class ThemePrefs @Inject constructor(
        private val pref: PrefManager
    ) : AppSharedPrefManager() {

        /**
         * Theme Prefs to retrieve current theme
         * Store current Theme
         */
        fun setTheme(theme: Theme) = pref.saveString("theme", theme.title)
        fun theme(): Theme {
            val theme = pref.loadString("theme", Theme.SYSTEM.title)
            return Theme.values().first { it.title == theme }
        }

        /**
         * Dynamic theme preference
         * Store current Theme and retrieve current dynamic theme pref
         */
        fun setDynamic(value: Boolean) = pref.saveBoolean("dynamic", value)
        fun dynamicColors() = pref.loadBoolean("dynamic", false)
    }
}