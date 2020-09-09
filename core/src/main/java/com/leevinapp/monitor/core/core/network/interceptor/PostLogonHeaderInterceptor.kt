package com.leevinapp.monitor.core.core.network.interceptor

import java.util.Calendar
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

abstract class PostLogonHeaderInterceptor : Interceptor {

    abstract fun getToken(): String

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val newRequest: Request

        newRequest = request.newBuilder()
            .addHeader("X-AUTH-TOKEN", getToken())
            .addHeader("X-AUTH-TIMESTAMP", Calendar.getInstance().timeInMillis.toString())
            .build()

        return chain.proceed(newRequest)
    }
}
