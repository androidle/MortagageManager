package com.leevinapp.monitor.core.core.network.mock

import android.content.Context
import com.leevinapp.monitor.core.core.network.ApiResponse
import java.nio.charset.StandardCharsets
import okio.BufferedSource
import okio.buffer
import okio.source

class MockApiUtil(val context: Context) {

    private val convertUtil = ConvertUtil()

    fun <T> fromJsonObject(json: String, clazz: Class<T>): T {
        return convertUtil.fromJsonObject(json, clazz)
    }

    fun <T> fromJsonArray(json: String, clazz: Class<T>): List<T> {
        return convertUtil.fromJsonArray(json, clazz)
    }

    fun <T> fromJsonObjectToApiResponse(json: String, clazz: Class<T>): ApiResponse<T> {
        return convertUtil.fromJsonObjectToApiResponse(json, clazz)
    }

    fun <T> fromJsonArrayToApiResponse(json: String, clazz: Class<T>): ApiResponse<List<T>> {
        return convertUtil.fromJsonArrayToApiResponse(json, clazz)
    }

    private fun readStringFromAssets(filename: String): String {
        val inputStream = context.resources.assets.open("mock/api/$filename")
        val source: BufferedSource = inputStream.source().buffer()
        val result = source.readString(StandardCharsets.UTF_8)
        source.close()
        return result
    }
}
