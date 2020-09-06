package com.leevinapp.monitor.mine.di

import com.leevinapp.monitor.core.core.di.CoreComponent
import com.leevinapp.monitor.core.core.di.scopes.FeatureScope
import com.leevinapp.monitor.mine.ui.MineFragment
import com.leevinapp.monitor.mine.ui.MinePersonalInfoFragment
import com.leevinapp.monitor.mine.ui.identityauth.MineIdentityAuthFragment
import dagger.Component

@FeatureScope
@Component(modules = [MineModule::class], dependencies = [CoreComponent::class])
interface MineComponent {
    fun inject(fragment: MineFragment)
    fun inject(fragment: MinePersonalInfoFragment)
    fun inject(fragment: MineIdentityAuthFragment)
}
