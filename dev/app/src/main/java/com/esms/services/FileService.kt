package com.esms.services
import android.content.Context

/**
 * Service class for storing and retrieving strings using SharedPreferences.
 * This class provides methods to write a string to SharedPreferences and
 * read it back. It's ideal for saving small amounts of data like user preferences.
 *
 * @param context Context used to access SharedPreferences.
 */
class SharedPreferencesService(private val context: Context) {

    private val sharedPreferencesName = "Parameters"
    private val key = "Encrypted"

    /**
     * Writes the given string to SharedPreferences.
     * This will overwrite any existing data stored under the same key.
     *
     * @param content The string to be written.
     */
    fun write(content: String) {
        val sharedPreferences = context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString(key, content)
            apply()
        }
    }

    /**
     * Reads and returns the string from SharedPreferences.
     * If there is no data available, it returns an empty string.
     *
     * @return The stored string or an empty string if no data is found.
     */
    fun read(): String {
        val sharedPreferences = context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, "") ?: ""
    }
}
