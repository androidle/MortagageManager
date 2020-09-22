package com.leevinapp.monitor.auth.ui

import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.leevinapp.monitor.auth.BuildConfig
import com.leevinapp.monitor.auth.R
import com.leevinapp.monitor.auth.R.string
import com.leevinapp.monitor.auth.data.api.response.LoginResponse
import com.leevinapp.monitor.auth.databinding.AuthFragmentLogonBinding
import com.leevinapp.monitor.auth.di.buildComponent
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.common.ui.base.ViewModelFragment
import com.leevinapp.monitor.core.common.view.CustomClickableSpan
import com.leevinapp.monitor.core.core.config.Constants
import com.leevinapp.monitor.core.core.storage.Storage
import com.leevinapp.monitor.core.core.user.UserManager
import kotlinx.android.synthetic.main.auth_fragment_logon.*
import javax.inject.Inject

class LogonFragment : ViewModelFragment() {

    @Inject
    lateinit var userManager: UserManager

    @Inject
    lateinit var storage: Storage

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
        buildComponent(this).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_logon.setOnClickListener {
            val isNoNeedLogon = storage.getBoolean(Constants.KEY_NO_NEED_LOGON)
            if (isNoNeedLogon && BuildConfig.DEBUG) {
                userManager.isLogged = true
                findNavController().navigateUp()
            } else {
                viewModel.login()
            }
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
            if (isChecked) {
            } else {
            }
        }

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                logonSuccess(it)
            }
        })

        viewModel.smsCodeResult.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), if (it) "发送成功" else "发送失败", Toast.LENGTH_SHORT)
        })
    }

    private fun logonSuccess(it: LoginResponse) {
        userManager.isLogged = true
        userManager.token = it.token
        with(userManager.user) {
            userId = it.id
            phoneNumber = it.telephone
            role = it.role
            fullname = it.fullName ?: ""
            email = it.email ?: ""
            nickname = it.nickname ?: ""
            organName = it.organizationName ?: ""
            organId = it.organizationId ?: 0
            jobPosition = it.jobPosition ?: ""
            isAuthenticated = it.isAuthenticated
            homeAddress = it.homeAddress ?: ""
            residenceId = it.residenceId?:""
        }

        findNavController().navigateUp()
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun onDestroy() {
        iev_sms_code?.cancelTimer()
        super.onDestroy()
    }
}
