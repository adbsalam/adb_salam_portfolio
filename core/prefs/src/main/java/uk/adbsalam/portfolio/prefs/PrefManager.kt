package uk.adbsalam.portfolio.prefs

import android.content.Context
import android.content.SharedPreferences

const val PREFS_NAME = "prefs"

class PrefManager(private val context: Context) {

    fun loadString(key: String, defaultValue: String = ""): String {
        val sharedPreferences = getWithFileName(context)
        return if (!sharedPreferences.contains(key)) {
            defaultValue
        } else {
            sharedPreferences.getString(key, defaultValue).orEmpty()
        }
    }

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

    private fun getWithFileName(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveString(key: String, value: String?) {
        getWithFileName(context).edit().putString(key, value).apply()
    }

    fun saveBoolean(key: String, value: Boolean) {
        getWithFileName(context).edit().putBoolean(key, value).apply()
    }

}