package com.leevinapp.monitor.core.core

import java.nio.charset.StandardCharsets
import okio.buffer
import okio.source

object CommonTestUtil {

    fun getJsonString(fileName: String): String {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val result = source.readString(StandardCharsets.UTF_8)
        source.close()
        return result
    }
}
