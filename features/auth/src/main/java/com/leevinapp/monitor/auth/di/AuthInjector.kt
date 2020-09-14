package com.leevinapp.monitor.auth.di

import androidx.fragment.app.Fragment
import com.leevinapp.monitor.core.core.di.CoreInjectHelper

fun buildComponent(fragment: Fragment): AuthComponent =
    DaggerAuthComponent.builder()
        .coreComponent(CoreInjectHelper.provideCoreComponent(fragment.requireContext()))
        .authModule(AuthModule(fragment))
        .build()
