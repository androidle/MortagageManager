package com.leevinapp.monitor.core.core.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
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

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            return networkInfo.isConnected
        }
    }
}
