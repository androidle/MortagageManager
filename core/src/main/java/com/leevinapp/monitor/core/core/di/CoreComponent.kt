package com.leevinapp.monitor.core.core.di

import android.content.Context
import com.leevinapp.monitor.core.core.network.NetworkModule
import com.leevinapp.monitor.core.core.user.UserManager
import dagger.Component
import javax.inject.Singleton
import retrofit2.Retrofit

@Singleton
@Component(modules = [CoreModule::class, NetworkModule::class])
interface CoreComponent {
    fun context(): Context
    fun retrofit(): Retrofit
    fun userManager(): UserManager
}
