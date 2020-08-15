package com.leevinapp.monitor.core.common.ui.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import timber.log.Timber

abstract class BaseFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.e("${this.javaClass.simpleName}====onCreate=====")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.e("${this.javaClass.simpleName}====onViewCreated=====")
    }

    override fun onAttach(context: Context) {
        onInitDependencyInjection()
        super.onAttach(context)
    }

    open fun onInitDependencyInjection() {
    }
}
