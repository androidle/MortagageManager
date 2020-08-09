package com.leevinapp.monitor.core.core.network.mock

import android.content.Context
import com.google.gson.Gson
import java.lang.reflect.Type
import java.nio.charset.StandardCharsets
import okio.BufferedSource
import okio.buffer
import okio.source

class MockApiUtil(val context: Context) {

    fun <T> getMockResponse(filename: String, type: Type): T {
        val resultJson: String = readStringFromAssets(filename)
        return Gson().fromJson(resultJson, type)
    }

    private fun readStringFromAssets(filename: String): String {
        val inputStream = context.resources.assets.open("mock/api/$filename")
        val source: BufferedSource = inputStream.source().buffer()
        val result = source.readString(StandardCharsets.UTF_8)
        source.close()
        return result
    }
}
