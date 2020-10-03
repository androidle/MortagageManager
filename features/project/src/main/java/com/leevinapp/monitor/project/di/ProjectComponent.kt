package com.leevinapp.monitor.project.di

import com.leevinapp.monitor.core.core.di.CoreComponent
import com.leevinapp.monitor.core.core.di.scopes.FeatureScope
import com.leevinapp.monitor.project.ui.ProjectCreateNewFragment
import com.leevinapp.monitor.project.ui.ProjectFragment
import com.leevinapp.monitor.project.ui.ProjectMineFragment
import com.leevinapp.monitor.project.ui.blueprint.ProjectCollateralFragment
import com.leevinapp.monitor.project.ui.blueprint.ProjectDailyLogFragment
import com.leevinapp.monitor.project.ui.blueprint.ProjectDetailsFragment
import dagger.Component

@FeatureScope
@Component(modules = [ProjectModule::class], dependencies = [CoreComponent::class])
interface ProjectComponent {
    fun inject(fragment: ProjectFragment)
    fun inject(fragment: ProjectCreateNewFragment)
    fun inject(fragment: ProjectMineFragment)
    fun inject(fragment: ProjectDetailsFragment)
    fun inject(fragment: ProjectCollateralFragment)
    fun inject(fragment: ProjectDailyLogFragment)
}
