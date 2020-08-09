package com.leevinapp.monitor.core.core.storage

import android.content.SharedPreferences

class EncryptedSharedPreferenceStorage(private val sharedPreferences: SharedPreferences) : Storage {
    override fun setString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun getString(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    override fun remove(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    override fun removeAll() {
        sharedPreferences.edit().clear().apply()
    }
}
