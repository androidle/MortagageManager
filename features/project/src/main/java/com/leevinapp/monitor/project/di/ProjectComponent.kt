package com.leevinapp.monitor.project.di

import com.leevinapp.monitor.core.core.di.CoreComponent
import com.leevinapp.monitor.core.core.di.scopes.FeatureScope
import com.leevinapp.monitor.project.ui.ProjectCreateNewFragment
import com.leevinapp.monitor.project.ui.ProjectFragment
import com.leevinapp.monitor.project.ui.ProjectMineProjectFragment
import dagger.Component

@FeatureScope
@Component(modules = [ProjectModule::class], dependencies = [CoreComponent::class])
interface ProjectComponent {
    fun inject(fragment: ProjectFragment)
    fun inject(fragment: ProjectCreateNewFragment)
    fun inject(fragment: ProjectMineProjectFragment)
}
