package com.leevinapp.monitor

import android.os.Bundle
import com.leevinapp.monitor.core.common.ui.base.BaseActivity
import com.leevinapp.monitor.core.core.di.CoreInjectHelper
import com.leevinapp.monitor.di.DaggerAppComponent

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerAppComponent.builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(this))
            .build()
            .inject(this)
    }
}
