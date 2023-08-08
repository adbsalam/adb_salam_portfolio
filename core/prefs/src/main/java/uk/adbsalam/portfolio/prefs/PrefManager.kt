package uk.adbsalam.portfolio.prefs

import android.content.Context
import android.content.SharedPreferences

const val PREFS_NAME = "prefs"

class PrefManager(private val context: Context) {

    /**
     * @param key key to store/read prefs as
     * @param defaultValue default String value to retrieve in pref
     */
    fun loadString(key: String, defaultValue: String = ""): String {
        val sharedPreferences = getWithFileName(context)
        return if (!sharedPreferences.contains(key)) {
            defaultValue
        } else {
            sharedPreferences.getString(key, defaultValue).orEmpty()
        }
    }


    /**
     * @param key key to store/read prefs as
     * @param defaultValue default Boolean value to retrieve in pref
     */
    fun loadBoolean(key: String, defaultValue: Boolean = false): Boolean {
        val sharedPreferences = getWithFileName(context)
        return if (!sharedPreferences.contains(key)) {
            defaultValue
        } else {
            try {
                sharedPreferences.getBoolean(key, defaultValue)
            } catch (e: Exception) {
                defaultValue
            }
        }
    }

    /**
     * String Pref File Name
     */
    private fun getWithFileName(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    /**
     * Save a string value to prefs
     */
    fun saveString(key: String, value: String?) {
        getWithFileName(context).edit().putString(key, value).apply()
    }

    /**
     * Save a boolean value to prefs
     */
    fun saveBoolean(key: String, value: Boolean) {
        getWithFileName(context).edit().putBoolean(key, value).apply()
    }

}