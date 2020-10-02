package com.leevinapp.monitor.project.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leevinapp.monitor.core.core.di.ViewModelKey
import com.leevinapp.monitor.core.core.di.scopes.FeatureScope
import com.leevinapp.monitor.project.ui.ProjectCreateNewViewModel
import com.leevinapp.monitor.project.ui.ProjectViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ProjectViewModelModule {

    @Binds
    @IntoMap
    @FeatureScope
    @ViewModelKey(ProjectCreateNewViewModel::class)
    internal abstract fun bindProjectCreateNewViewModel(viewModel: ProjectCreateNewViewModel): ViewModel

    @Binds
    @IntoMap
    @FeatureScope
    @ViewModelKey(ProjectViewModel::class)
    internal abstract fun bindProjectViewModel(viewModel: ProjectViewModel): ViewModel

    @Binds
    @FeatureScope
    internal abstract fun bindViewModelFactory(factory: ProjectViewModelFactory): ViewModelProvider.Factory
}