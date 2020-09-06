package com.leevinapp.monitor.core.core.network

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.leevinapp.monitor.core.BuildConfig
import com.leevinapp.monitor.core.core.network.interceptor.HeaderInterceptor
import com.leevinapp.monitor.core.core.network.interceptor.PostLogonHeaderInterceptor
import com.leevinapp.monitor.core.core.network.mock.MockApiUtil
import com.leevinapp.monitor.core.core.user.UserManager
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providerBaseUrl(): String {
        // TODO: 2020/8/6 to test
        // return "https://api.github.com/"
        return "http://122.9.47.171:10002/gw/collateral/"
    }

    @Singleton
    @Provides
    fun providerRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun providerOkHttpClient(userManager: UserManager): OkHttpClient {
        // TODO: 2020/8/6 log network ,header interceptor
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(StethoInterceptor())
            builder.addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
        }

        // todo how to add header for all post-logon API
        if (userManager.isLogged) {
            builder.addInterceptor(object : PostLogonHeaderInterceptor() {
                override fun getToken(): String {
                    return userManager.token
                }
            })
        }

        return builder
            .addInterceptor(HeaderInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun providerMockApiUtil(context: Context): MockApiUtil {
        return MockApiUtil(context)
    }
}
