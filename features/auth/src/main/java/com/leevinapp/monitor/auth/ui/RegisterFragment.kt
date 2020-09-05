package com.leevinapp.monitor.auth.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.leevinapp.monitor.auth.databinding.AuthFragmentRegisterBinding
import com.leevinapp.monitor.auth.di.AuthModule
import com.leevinapp.monitor.auth.di.DaggerAuthComponent
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.core.core.di.CoreInjectHelper
import com.leevinapp.monitor.core.core.user.UserManager
import javax.inject.Inject

class RegisterFragment : BaseFragment() {

    @Inject
    lateinit var userManager: UserManager

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: RegisterViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return AuthFragmentRegisterBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@RegisterFragment.viewModel
        }.root
    }

    override fun initDependencyInjection() {
        DaggerAuthComponent.builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(requireContext()))
            .authModule(AuthModule(this))
            .build()
            .inject(this)
    }
}
