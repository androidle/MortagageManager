package com.leevinapp.monitor.mine.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leevinapp.monitor.auth.di.AuthViewModelFactory
import com.leevinapp.monitor.core.core.di.ViewModelKey
import com.leevinapp.monitor.core.core.di.scopes.FeatureScope
import com.leevinapp.monitor.mine.ui.ApplyParentOrganViewModel
import com.leevinapp.monitor.mine.ui.MineViewModel
import com.leevinapp.monitor.mine.ui.PersonalInfoViewModel
import com.leevinapp.monitor.mine.ui.identityauth.IdentityAuthViewModel
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
    @IntoMap
    @FeatureScope
    @ViewModelKey(IdentityAuthViewModel::class)
    internal abstract fun bindIdentityAuthViewModel(viewModel: IdentityAuthViewModel): ViewModel


    @Binds
    @IntoMap
    @FeatureScope
    @ViewModelKey(ApplyParentOrganViewModel::class)
    internal abstract fun bindApplyParentOrganViewModel(viewModel: ApplyParentOrganViewModel): ViewModel

    @Binds
    @FeatureScope
    internal abstract fun bindViewModelFactory(factory: AuthViewModelFactory): ViewModelProvider.Factory
}
