package com.leevinapp.monitor.core.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val newRequest: Request

        newRequest = request.newBuilder()
            // TODO: 2020/8/6
            // .addHeader("Accept-Language", "en-us")
           // .addHeader("Content-Type", "application/json")
            // .addHeader("X-AUTH-TOKEN", "")
            // .addHeader("X-AUTH-TIMESTAMP", "")
            // .addHeader("X-DEVICE", "")
            // .addHeader("X-VERSION", "")
            .build()

        return chain.proceed(newRequest)
    }
}
