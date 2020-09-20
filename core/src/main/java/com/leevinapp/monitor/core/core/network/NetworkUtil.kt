package com.leevinapp.monitor.core.core.network

import com.leevinapp.monitor.core.core.network.interceptor.PostLogonHeaderInterceptor
import com.leevinapp.monitor.core.core.user.UserManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit

object NetworkUtil {

    fun postLogonRetrofit(
        retrofit: Retrofit,
        okHttpClient: OkHttpClient,
        userManager: UserManager
    ): Retrofit {
        val postLogonHeaderInterceptor = object : PostLogonHeaderInterceptor() {
            override fun getToken(): String {
                return userManager.token
            }
        }
        val newClientBuilder = okHttpClient.newBuilder()
        newClientBuilder
            .interceptors()
            .add(0, postLogonHeaderInterceptor)

        return retrofit
            .newBuilder()
            .client(newClientBuilder.build())
            .build()
    }
}
