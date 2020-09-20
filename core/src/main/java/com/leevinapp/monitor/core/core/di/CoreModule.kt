package com.leevinapp.monitor.core.core.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV
import androidx.security.crypto.EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
import androidx.security.crypto.MasterKeys
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
        // TODO: 2020/9/19 to be look into new method
        // val builder = MasterKey.Builder(context)
        //
        // builder.setKeyGenParameterSpec(MasterKeys.AES256_GCM_SPEC)
        // builder.setKeyScheme(valueOf())
        //
        // return EncryptedSharedPreferences.create(
        //     context,
        //     "monitor_preferences",
        //     masterKey,
        //     EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        //     EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        // )

        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        return EncryptedSharedPreferences.create(
            "monitor_preferences",
            masterKeyAlias,
            context,
            AES256_SIV,
            AES256_GCM
        )
    }

    @Singleton
    @Provides
    fun provideStorage(sharedPreferences: SharedPreferences): Storage {
        return EncryptedSharedPreferenceStorage(sharedPreferences)
    }
}
