package com.leevinapp.monitor.auth.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.leevinapp.monitor.auth.databinding.AuthFragmentLogonBinding
import com.leevinapp.monitor.auth.di.AuthModule
import com.leevinapp.monitor.auth.di.DaggerAuthComponent
import com.leevinapp.monitor.auth.domain.model.AuthModel
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.core.common.ui.extensions.hideLoadingDialog
import com.leevinapp.monitor.core.common.ui.extensions.showLoadingDialog
import com.leevinapp.monitor.core.core.di.CoreInjectHelper
import com.leevinapp.monitor.core.core.user.UserManager
import kotlinx.android.synthetic.main.auth_fragment_logon.*
import javax.inject.Inject

class LogonFragment : BaseFragment() {

    @Inject
    lateinit var userManager: UserManager

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: LogonViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return AuthFragmentLogonBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@LogonFragment.viewModel
        }.root
    }

    override fun initDependencyInjection() {
        DaggerAuthComponent.builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(requireContext()))
            .authModule(AuthModule(this))
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_logon.setOnClickListener {
            viewModel.login()
        }

        tv_to_register.setOnClickListener {
            findNavController().navigate(LogonFragmentDirections.authActionLogonfragmentToRegisterfragment())
        }
        
        tv_forgot_password.setOnClickListener {
            // TODO: 2020/9/8  
        }

        button_sms_code.setOnClickListener {
            countDownTimer.start()
            viewModel.sendSmsCode()
        }
        
        cb_auto_login.setOnCheckedChangeListener { buttonView, isChecked ->
            userManager.isLogged = isChecked
            // TODO: 2020/9/8  
            if (isChecked) {
                
            } else {
                
            }
        }

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                requireActivity().showLoadingDialog()
            } else {
                requireActivity().hideLoadingDialog()
            }
        })

        viewModel.authModel.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                updateLogonStatus(it)
                findNavController().navigateUp()
            }
        })
    }

    private fun updateLogonStatus(it: AuthModel) {
        userManager.isLogged = true
        userManager.token = it.token
        userManager.user.phoneNumber = it.telephone
        userManager.user.role = it.role
        userManager.user.userFullName = it.role
    }

    val countDownTimer = object : CountDownTimer(60 * 1000, 1000) {
        override fun onFinish() {
            button_sms_code.text = "重新获取"
            button_sms_code.isEnabled = true
        }

        override fun onTick(millisUntilFinished: Long) {
            button_sms_code.text = "${millisUntilFinished / 1000}s 后重新发送"
            button_sms_code.isEnabled = false
        }
    }

    override fun onDestroy() {
        countDownTimer.cancel()
        super.onDestroy()
    }
}
