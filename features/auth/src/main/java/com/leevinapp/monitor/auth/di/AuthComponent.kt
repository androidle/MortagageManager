package com.leevinapp.monitor.auth.di

import com.leevinapp.monitor.auth.ui.ChangePasswordFragment
import com.leevinapp.monitor.auth.ui.ForgotPasswordFragment
import com.leevinapp.monitor.auth.ui.LogonFragment
import com.leevinapp.monitor.auth.ui.RegisterFragment
import com.leevinapp.monitor.auth.ui.ResetPasswordFragment
import com.leevinapp.monitor.core.core.di.CoreComponent
import com.leevinapp.monitor.core.core.di.scopes.FeatureScope
import dagger.Component

@FeatureScope
@Component(modules = [AuthModule::class], dependencies = [CoreComponent::class])
interface AuthComponent {
    fun inject(fragment: LogonFragment)
    fun inject(fragment: RegisterFragment)
    fun inject(fragment: ForgotPasswordFragment)
    fun inject(fragment: ResetPasswordFragment)
    fun inject(fragment: ChangePasswordFragment)
}
