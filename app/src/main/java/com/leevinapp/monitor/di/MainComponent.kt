package com.leevinapp.monitor.di

import com.leevinapp.monitor.MainFragment
import com.leevinapp.monitor.core.core.di.CoreComponent
import com.leevinapp.monitor.core.core.di.scopes.FeatureScope
import dagger.Component

@FeatureScope
@Component(modules = [MainModule::class], dependencies = [CoreComponent::class])
interface MainComponent {
    fun inject(fragment: MainFragment)
}
