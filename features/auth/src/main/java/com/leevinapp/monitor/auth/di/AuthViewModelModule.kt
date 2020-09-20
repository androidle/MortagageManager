package com.leevinapp.monitor.auth.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leevinapp.monitor.auth.ui.ChangePasswordViewModel
import com.leevinapp.monitor.auth.ui.LogonViewModel
import com.leevinapp.monitor.auth.ui.RegisterViewModel
import com.leevinapp.monitor.auth.ui.ResetPasswordViewModel
import com.leevinapp.monitor.auth.ui.VerifyEmailViewModel
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
    @IntoMap
    @FeatureScope
    @ViewModelKey(RegisterViewModel::class)
    internal abstract fun bindRegisterViewModel(registerViewModel: RegisterViewModel): ViewModel

    @Binds
    @IntoMap
    @FeatureScope
    @ViewModelKey(ResetPasswordViewModel::class)
    internal abstract fun bindResetPasswordViewModel(resetPasswordViewModel: ResetPasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @FeatureScope
    @ViewModelKey(ChangePasswordViewModel::class)
    internal abstract fun bindChangePasswordViewModel(changePasswordViewModel: ChangePasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @FeatureScope
    @ViewModelKey(VerifyEmailViewModel::class)
    internal abstract fun bindVerifyEmailViewModel(resetPasswordViewModel: VerifyEmailViewModel): ViewModel

    @Binds
    @FeatureScope
    internal abstract fun bindViewModelFactory(factory: AuthViewModelFactory): ViewModelProvider.Factory
}
