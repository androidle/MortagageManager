package com.leevinapp.monitor.core.core.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.leevinapp.monitor.core.BuildConfig
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
        return "https://api.github.com/"
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
        // TODO: 2020/8/6 log network ,header interceptor
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(StethoInterceptor())
            builder.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }

        return builder
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }
}
