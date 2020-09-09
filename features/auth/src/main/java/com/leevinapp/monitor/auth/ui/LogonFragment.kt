package com.leevinapp.monitor.auth.ui

import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.leevinapp.monitor.auth.R
import com.leevinapp.monitor.auth.R.string
import com.leevinapp.monitor.auth.data.api.response.LoginResponse
import com.leevinapp.monitor.auth.databinding.AuthFragmentLogonBinding
import com.leevinapp.monitor.auth.di.AuthModule
import com.leevinapp.monitor.auth.di.DaggerAuthComponent
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.core.common.ui.extensions.hideLoadingDialog
import com.leevinapp.monitor.core.common.ui.extensions.showLoadingDialog
import com.leevinapp.monitor.core.common.view.CustomClickableSpan
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

        val spannableString = SpannableString(getString(string.auth_unregistered_to_register))
        spannableString.setSpan(
            CustomClickableSpan(requireContext()) {
                findNavController().navigate(LogonFragmentDirections.authActionLogonfragmentToRegisterfragment())
            },
            spannableString.length - 2,
            spannableString.length,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        tv_to_register.text = spannableString
        tv_to_register.movementMethod = LinkMovementMethod.getInstance()
        tv_forgot_password.setOnClickListener {
            findNavController().navigate(R.id.auth_action_logonfragment_to_forgotpasswordfragment)
        }

        iev_sms_code.setSmsCodeClickListener {
            iev_sms_code.startTimer()
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

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                updateLogonStatus(it)
                findNavController().navigateUp()
            }
        })
    }

    private fun updateLogonStatus(it: LoginResponse) {
        userManager.isLogged = true
        userManager.token = it.token
        with(userManager.user) {
            userId = it.id
            phoneNumber = it.telephone
            role = it.role ?: ""
            fullname = it.fullName ?: ""
            email = it.email ?: ""
            nickname = it.nickname ?: ""
            organName = it.organizationName ?: ""
            organId = it.organizationId ?: 0
            jobPosition = it.jobPosition ?: ""
            securityQuestion = it.securityQuestion ?: ""
            securityAnswer = it.securityAnswer ?: ""
            isAuthenticated = it.isAuthenticated
            homeAddress = it.homeAddress ?: ""
            residenceId = it.residenceId ?: 0
        }
    }

    override fun onDestroy() {
        iev_sms_code?.cancelTimer()
        super.onDestroy()
    }
}
