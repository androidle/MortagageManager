package com.leevinapp.monitor.core.core.storage

interface Storage {
    fun setString(key: String, value: String)
    fun getString(key: String, defaultValue: String = ""): String
    fun remove(key: String)
    fun removeAll()
}