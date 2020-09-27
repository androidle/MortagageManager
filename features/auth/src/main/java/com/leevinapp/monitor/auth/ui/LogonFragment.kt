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
import com.leevinapp.monitor.core.core.config.Constants.KEY_IS_REMEMBER_LOGON_NAME
import com.leevinapp.monitor.core.core.config.Constants.KEY_PASSWORD
import com.leevinapp.monitor.core.core.config.Constants.KEY_PHONE_NUMBER
import com.leevinapp.monitor.core.core.storage.Storage
import com.leevinapp.monitor.core.core.user.UserManager
import com.leevinapp.monitor.core.core.utils.autoCleared
import javax.inject.Inject
import kotlinx.android.synthetic.main.auth_fragment_logon.*

class LogonFragment : ViewModelFragment() {

    @Inject
    lateinit var userManager: UserManager

    @Inject
    lateinit var storage: Storage

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var viewBinding by autoCleared<AuthFragmentLogonBinding>()

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
            viewBinding = this
            viewModel = this@LogonFragment.viewModel
        }.root
    }

    override fun initDependencyInjection() {
        buildComponent(this).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        echoLogonName()

        viewBinding.buttonLogon.setOnClickListener {
            val isNoNeedLogon = storage.getBoolean(Constants.KEY_IS_NO_NEED_LOGON)
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

        viewBinding.tvToRegister.text = spannableString
        tv_to_register.movementMethod = LinkMovementMethod.getInstance()
        tv_forgot_password.setOnClickListener {
            findNavController().navigate(R.id.auth_action_logonfragment_to_forgotpasswordfragment)
        }

        viewBinding.ievSmsCode.setSmsCodeClickListener {
            iev_sms_code.startTimer()
            viewModel.sendSmsCode()
        }

        viewBinding.cbAutoLogin.setOnCheckedChangeListener { _, isChecked ->
            userManager.isLogged = isChecked
            if (isChecked) {
                storage.setBoolean(KEY_IS_REMEMBER_LOGON_NAME, true)
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

    private fun echoLogonName() {
        if (storage.getBoolean(KEY_IS_REMEMBER_LOGON_NAME)) {
            val phoneNumber = storage.getString(KEY_PHONE_NUMBER)
            val password = storage.getString(KEY_PASSWORD)
            viewModel.phoneNumber.value = phoneNumber
            viewModel.password.value = password
            viewBinding.cbAutoLogin.isChecked = true
        }
    }

    private fun logonSuccess(it: LoginResponse) {
        userManager.isLogged = true
        userManager.token = it.token
        with(userManager.user) {
            id = it.id
            phoneNumber = it.telephone
            role = it.role
            fullName = it.fullName ?: ""
            email = it.email ?: ""
            nickname = it.nickname ?: ""
            organName = it.organizationName ?: ""
            organId = it.organizationId ?: 0
            jobPosition = it.jobPosition ?: ""
            isAuthenticated = it.isAuthenticated
            homeAddress = it.homeAddress ?: ""
            residenceId = it.residenceId ?: ""
        }
        if (storage.getBoolean(KEY_IS_REMEMBER_LOGON_NAME)) {
            storage.setString(KEY_PHONE_NUMBER, viewModel.phoneNumber.value ?: "")
            storage.setString(KEY_PASSWORD, viewModel.password.value ?: "")
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
