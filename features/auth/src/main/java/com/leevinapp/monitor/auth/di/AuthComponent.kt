package com.leevinapp.monitor.auth.di

import com.leevinapp.monitor.auth.ui.LogonFragment
import com.leevinapp.monitor.core.core.di.CoreComponent
import com.leevinapp.monitor.core.core.di.scopes.FeatureScope
import dagger.Component

@FeatureScope
@Component(modules = [AuthModule::class], dependencies = [CoreComponent::class])
interface AuthComponent {
    fun inject(fragment: LogonFragment)
}
