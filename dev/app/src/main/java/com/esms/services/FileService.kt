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
    private val KEY_SEPARATOR_CHAR      = "\u0009"  // \u0009 = HORIZONTAL TAB in ASCII
    private val PAIR_SEPARATOR_CHAR     = "\u0006"  // \u0006 = ACKNOWLEDGE in ASCII
    private val MAP_SEPARATOR_CHAR      = "\u000B"  // \u000B = VERTICAL TAB in ASCII
    private val MAP_NAME_SEPARATOR_CHAR = "\u001F"  // \u001F = UNIT SEPARATOR in ASCII

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

    /**
     * Stringifies the given map into our format
     * @param map any Map<String, String> that does not contain [KEY_SEPARATOR_CHAR],[PAIR_SEPARATOR_CHAR],[MAP_SEPARATOR_CHAR],[MAP_NAME_SEPARATOR_CHAR]
     * @return a simplified-JSON string representing [map]
     */
    fun stringifyMap(map: Map<String, String>): String {
        return map.entries.joinToString(separator = PAIR_SEPARATOR_CHAR) { (key, value) ->
            "$key$KEY_SEPARATOR_CHAR$value"
        }
    }
    /**
     * Stringifies the given map into our format
     * @param map any Map<String, String> that does not contain [KEY_SEPARATOR_CHAR] or [PAIR_SEPARATOR_CHAR]
     * @return a simplified-JSON string representing [map]
     */
    fun stringifyMapMap(map: Map<String, Map<String, String>>): String {
        return map.entries.joinToString(separator = MAP_SEPARATOR_CHAR) { (key, value) ->
            "$key$MAP_NAME_SEPARATOR_CHAR${stringifyMap(value)}"
        }
    }

    /**
     * Parses a simplified-JSON string
     *
     * @param str a simplified-JSON string representing a Map<String, String>
     * @return Map<String, String> represented by [str]
     */
    fun destringifyOneMap(str: String): Map<String, String> {
        return str
            .split(PAIR_SEPARATOR_CHAR)
            .associate {
                val (key, value, _) = it.split(KEY_SEPARATOR_CHAR) + listOf("", "")
                key to value
            }
    }
    /**
     * Parses a simplified-JSON string
     *
     * @param str a simplified-JSON string representing a Map<String, Map<String, String>>
     * @return Map<String, Map<String, String>> represented by [str]
     */
    fun destringifyMaps(str: String): Map<String, Map<String, String>> {
        return str
            .split(MAP_SEPARATOR_CHAR)
            .associate {
                val (key, value, _) = it.split(MAP_NAME_SEPARATOR_CHAR) + listOf("", "")
                key to destringifyOneMap(value)
            }
    }
}
