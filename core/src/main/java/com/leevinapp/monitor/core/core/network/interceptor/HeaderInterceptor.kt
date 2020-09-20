package com.leevinapp.monitor.core.core.network.interceptor

import android.os.Build
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader("OSVersion", Build.VERSION.RELEASE)
            .addHeader("Device", Build.MODEL)
            .build()
        return chain.proceed(newRequest)
    }
}
