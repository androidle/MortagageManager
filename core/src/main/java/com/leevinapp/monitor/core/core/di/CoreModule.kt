package com.leevinapp.monitor.core.core.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.leevinapp.monitor.core.core.storage.EncryptedSharedPreferenceStorage
import com.leevinapp.monitor.core.core.storage.Storage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideContext(): Context = application

    @Singleton
    @Provides
    fun provideSharePreferences(context: Context): SharedPreferences {
        // TODO: 2020/8/6
        val masterKey = MasterKey.Builder(context).build()
        return EncryptedSharedPreferences.create(
            context,
            "monitor_preferences",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @Singleton
    @Provides
    fun provideStorage(sharedPreferences: SharedPreferences): Storage {
        return EncryptedSharedPreferenceStorage(sharedPreferences)
    }
}
