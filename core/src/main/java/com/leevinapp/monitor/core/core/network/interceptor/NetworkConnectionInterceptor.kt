package com.leevinapp.monitor.core.core.network.interceptor

import com.leevinapp.monitor.core.core.network.exception.ResponseException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

abstract class NetworkConnectionInterceptor : Interceptor {

    abstract fun isNetworkAvailable(): Boolean

    @Throws(ResponseException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkAvailable()) {
            throw ResponseException.noNetworkException()
        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}
