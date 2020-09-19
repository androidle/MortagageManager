package com.leevinapp.monitor.mine.di

import com.leevinapp.monitor.core.core.di.CoreComponent
import com.leevinapp.monitor.core.core.di.scopes.FeatureScope
import com.leevinapp.monitor.mine.ui.ApplyParentInstitutionFragment
import com.leevinapp.monitor.mine.ui.MineFragment
import com.leevinapp.monitor.mine.ui.GeneralInfoFragment
import com.leevinapp.monitor.mine.ui.generalInfo.MinePersonalInfoFragment
import com.leevinapp.monitor.mine.ui.identityauth.MineIdentityAuthFragment
import com.leevinapp.monitor.mine.ui.identityauth.MortgageUserAuthFragment
import com.leevinapp.monitor.mine.ui.identityauth.OrdinaryUserAuthFragment
import com.leevinapp.monitor.mine.ui.identityauth.OrganizationAuthFragment
import dagger.Component

@FeatureScope
@Component(modules = [MineModule::class], dependencies = [CoreComponent::class])
interface MineComponent {
    fun inject(fragment: MineFragment)
    fun inject(fragment: MinePersonalInfoFragment)
    fun inject(fragment: MineIdentityAuthFragment)
    fun inject(fragment: MortgageUserAuthFragment)
    fun inject(fragment: OrdinaryUserAuthFragment)
    fun inject(fragment: OrganizationAuthFragment)
    fun inject(fragment: ApplyParentInstitutionFragment)
    fun inject(fragment: GeneralInfoFragment)
}
