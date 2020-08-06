package com.leevinapp.monitor.core.common.ui.base

import android.app.Application
import com.facebook.stetho.Stetho
import com.leevinapp.monitor.core.BuildConfig
import com.leevinapp.monitor.core.core.di.CoreComponent
import com.leevinapp.monitor.core.core.di.CoreComponentProvider
import com.leevinapp.monitor.core.core.di.CoreModule
import com.leevinapp.monitor.core.core.di.DaggerCoreComponent
import timber.log.Timber

abstract class BaseApplication : Application(), CoreComponentProvider {

    override fun onCreate() {
        super.onCreate()
        initDebugUtils()
    }

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent
            .builder()
            .coreModule(CoreModule(this))
            .build()
    }

    private fun initDebugUtils() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun provideCoreComponent(): CoreComponent {
        return coreComponent
    }
}
