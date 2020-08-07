package com.leevinapp.monitor.core.common.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.e("${this.javaClass.simpleName}====onCreate=====")
    }
}
