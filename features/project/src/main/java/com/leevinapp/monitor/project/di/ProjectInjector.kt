package com.leevinapp.monitor.project.di

import androidx.fragment.app.Fragment
import com.leevinapp.monitor.core.core.di.CoreInjectHelper

fun buildComponent(fragment: Fragment): ProjectComponent =
    DaggerProjectComponent
        .builder()
        .coreComponent(CoreInjectHelper.provideCoreComponent(fragment.requireContext()))
        .projectModule(ProjectModule())
        .build()
