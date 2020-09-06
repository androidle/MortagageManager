package com.leevinapp.monitor.mine.di

import androidx.fragment.app.Fragment
import com.leevinapp.monitor.core.core.di.CoreInjectHelper

fun buildComponent(fragment:Fragment): MineComponent =
    DaggerMineComponent
        .builder()
        .coreComponent(CoreInjectHelper.provideCoreComponent(fragment.requireContext()))
        .mineModule(MineModule())
        .build()