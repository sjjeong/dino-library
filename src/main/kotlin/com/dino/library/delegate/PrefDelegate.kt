package com.dino.library.delegate

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.dino.library.ext.gson
import com.dino.library.ext.toJson
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PrefDelegate<T>(
    private val sharedPreferences: SharedPreferences,
    private val key: String,
    private val default: T? = null,
    private val type: Class<*>? = null
) : ReadWriteProperty<Any, T> {

    @Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return when (default) {
            is Boolean -> sharedPreferences.getBoolean(key, default)
            is Float -> sharedPreferences.getFloat(key, default)
            is Int -> sharedPreferences.getInt(key, default)
            is Long -> sharedPreferences.getLong(key, default)
            is String -> sharedPreferences.getString(key, default)
            else -> {
                if (default == null && type == null) {
                    error("if default is null, type must not be null. because when Gson.fromJson() is invoked, type must not be null.")
                }
                val jsonString = sharedPreferences.getString(key, "")
                gson.fromJson(jsonString, type)
            }
        } as T
    }

    @SuppressLint("CommitPrefEdits")
    @Suppress("IMPLICIT_CAST_TO_ANY")
    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        with(sharedPreferences.edit()) {
            when (value) {
                is Boolean -> putBoolean(key, value)
                is Float -> putFloat(key, value)
                is Int -> putInt(key, value)
                is Long -> putLong(key, value)
                is String -> putString(key, value)
                else -> {
                    putString(key, value.toJson())
                }
            }.apply()

        }
    }
}
