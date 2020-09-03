package com.leevinapp.monitor.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leevinapp.monitor.MainViewModel
import com.leevinapp.monitor.core.core.di.ViewModelKey
import com.leevinapp.monitor.core.core.di.scopes.FeatureScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @FeatureScope
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @FeatureScope
    internal abstract fun bindViewModelFactory(factory: MainViewModelFactory): ViewModelProvider.Factory
}
