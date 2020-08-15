package com.leevinapp.monitor.core.common.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class ViewModelFragment<VB : ViewDataBinding,M : ViewModel> : BaseFragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate<VB>(inflater, getLayoutId(), container, false)
        return binding.root
    }

    abstract fun getLayoutId(): Int

    override fun onInitDependencyInjection() {
        super.onInitDependencyInjection()
    }



}