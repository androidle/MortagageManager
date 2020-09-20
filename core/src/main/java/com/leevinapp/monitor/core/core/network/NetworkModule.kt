package com.leevinapp.monitor.core.core.network

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.leevinapp.monitor.core.BuildConfig
import com.leevinapp.monitor.core.core.network.interceptor.HeaderInterceptor
import com.leevinapp.monitor.core.core.network.interceptor.NetworkConnectionInterceptor
import com.leevinapp.monitor.core.core.network.mock.MockApiUtil
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

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
    fun providerOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder
            .addInterceptor(object : NetworkConnectionInterceptor() {
                override fun isNetworkAvailable(): Boolean {
                    // TODO: 2020/9/19
                    return true
                }
            })

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HeaderInterceptor())
            builder.addNetworkInterceptor(StethoInterceptor())
            builder.addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
        }
        return builder
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
