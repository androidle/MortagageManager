package com.leevinapp.monitor.mine.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leevinapp.monitor.auth.di.AuthViewModelFactory
import com.leevinapp.monitor.core.core.di.ViewModelKey
import com.leevinapp.monitor.core.core.di.scopes.FeatureScope
import com.leevinapp.monitor.mine.ui.MineViewModel
import com.leevinapp.monitor.mine.ui.PersonalInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MineViewModelModule {

    @Binds
    @IntoMap
    @FeatureScope
    @ViewModelKey(MineViewModel::class)
    internal abstract fun bindLogonViewModel(viewModel: MineViewModel): ViewModel

    @Binds
    @IntoMap
    @FeatureScope
    @ViewModelKey(PersonalInfoViewModel::class)
    internal abstract fun bindPersonalInfoViewModel(viewModel: PersonalInfoViewModel): ViewModel

    @Binds
    @FeatureScope
    internal abstract fun bindViewModelFactory(factory: AuthViewModelFactory): ViewModelProvider.Factory
}
