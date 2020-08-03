package com.leevinapp.monitor.core.core.di

import android.content.Context

interface CoreComponentProvider{
    fun provideCoreComponent() : CoreComponent
}
class CoreInjectHelper {
    companion object {
        fun provideCoreComponent(context: Context): CoreComponent {
            return if (context.applicationContext is CoreComponentProvider) {
                return (context.applicationContext as CoreComponentProvider).provideCoreComponent()
            } else {
                throw IllegalArgumentException("The application context you gave does not implement CoreComponentProvider")
            }
        }
    }
}