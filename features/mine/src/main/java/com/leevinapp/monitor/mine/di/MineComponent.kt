package com.leevinapp.monitor.mine.di

import com.leevinapp.monitor.core.core.di.CoreComponent
import com.leevinapp.monitor.core.core.di.scopes.FeatureScope
import com.leevinapp.monitor.mine.ui.ApplyAccessTransferFragment
import com.leevinapp.monitor.mine.ui.ApplyAttachedInstitutionFragment
import com.leevinapp.monitor.mine.ui.ApplyParentInstitutionFragment
import com.leevinapp.monitor.mine.ui.GeneralInfoFragment
import com.leevinapp.monitor.mine.ui.InstitutionUserFragment
import com.leevinapp.monitor.mine.ui.MineFragment
import com.leevinapp.monitor.mine.ui.NotificationDetailsFragment
import com.leevinapp.monitor.mine.ui.NotificationsFragment
import com.leevinapp.monitor.mine.ui.SubInstitutionsFragment
import com.leevinapp.monitor.mine.ui.generalInfo.PersonalInfoFragment
import com.leevinapp.monitor.mine.ui.identityauth.MortgageUserAuthFragment
import com.leevinapp.monitor.mine.ui.identityauth.OrdinaryUserAuthFragment
import com.leevinapp.monitor.mine.ui.identityauth.OrganizationAuthFragment
import com.leevinapp.monitor.mine.ui.ticket.TicketStatusFragment
import com.leevinapp.monitor.mine.ui.ticket.TicketsFragment
import dagger.Component

@FeatureScope
@Component(modules = [MineModule::class], dependencies = [CoreComponent::class])
interface MineComponent {
    fun inject(fragment: MineFragment)
    fun inject(fragment: PersonalInfoFragment)
    fun inject(fragment: MortgageUserAuthFragment)
    fun inject(fragment: OrdinaryUserAuthFragment)
    fun inject(fragment: OrganizationAuthFragment)
    fun inject(fragment: GeneralInfoFragment)
    fun inject(fragment: NotificationDetailsFragment)
    fun inject(fragment: SubInstitutionsFragment)
    fun inject(fragment: NotificationsFragment)
    fun inject(fragment: TicketsFragment)
    fun inject(fragment: InstitutionUserFragment)
    fun inject(fragment: TicketStatusFragment)
    fun inject(fragment: ApplyParentInstitutionFragment)
    fun inject(fragment: ApplyAccessTransferFragment)
    fun inject(fragment: ApplyAttachedInstitutionFragment)
}
