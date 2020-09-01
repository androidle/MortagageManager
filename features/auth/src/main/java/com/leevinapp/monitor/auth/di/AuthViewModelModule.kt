package com.leevinapp.monitor.auth.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leevinapp.monitor.auth.ui.LogonViewModel
import com.leevinapp.monitor.core.core.di.ViewModelKey
import com.leevinapp.monitor.core.core.di.scopes.FeatureScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @FeatureScope
    @ViewModelKey(LogonViewModel::class)
    internal abstract fun bindLogonViewModel(logonViewModel: LogonViewModel): ViewModel

    @Binds
    @FeatureScope
    internal abstract fun bindViewModelFactory(factory: AuthViewModelFactory): ViewModelProvider.Factory
}
