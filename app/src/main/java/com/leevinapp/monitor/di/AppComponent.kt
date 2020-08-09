package com.leevinapp.monitor.di

import com.leevinapp.monitor.MainActivity
import com.leevinapp.monitor.core.core.di.CoreComponent
import com.leevinapp.monitor.core.core.di.scopes.ActivityScope
import dagger.Component

@ActivityScope
@Component(dependencies = [CoreComponent::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}
