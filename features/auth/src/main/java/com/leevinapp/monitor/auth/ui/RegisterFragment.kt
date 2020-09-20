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
import com.leevinapp.monitor.auth.R
import com.leevinapp.monitor.auth.databinding.AuthFragmentRegisterBinding
import com.leevinapp.monitor.auth.di.AuthModule
import com.leevinapp.monitor.auth.di.DaggerAuthComponent
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.common.ui.base.ViewModelFragment
import com.leevinapp.monitor.core.common.view.CustomClickableSpan
import com.leevinapp.monitor.core.core.di.CoreInjectHelper
import com.leevinapp.monitor.core.core.user.UserManager
import javax.inject.Inject
import kotlinx.android.synthetic.main.auth_fragment_register.*

class RegisterFragment : ViewModelFragment() {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_register.setOnClickListener {
            viewModel.registerUser()
        }

        iev_sms_code.setSmsCodeClickListener {
            iev_sms_code.startTimer()
            viewModel.sendSmsCode()
        }

        val spannableString = SpannableString(getString(R.string.auth_registered_to_login))
        spannableString.setSpan(
            CustomClickableSpan(requireContext()) {
                findNavController().navigateUp()
            },
            spannableString.length - 2,
            spannableString.length,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tv_to_login.text = spannableString
        tv_to_login.movementMethod = LinkMovementMethod.getInstance()

        viewModel.registerSuccessToken.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                userManager.token = it
                findNavController().popBackStack(R.id.logonFragment, true)
            }
        })

        viewModel.smsCodeResult.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), if (it) "发送成功" else "发送失败", Toast.LENGTH_SHORT)
        })
    }

    override fun initDependencyInjection() {
        DaggerAuthComponent.builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(requireContext()))
            .authModule(AuthModule(this))
            .build()
            .inject(this)
    }

    override fun onDestroy() {
        iev_sms_code?.cancelTimer()
        super.onDestroy()
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }
}
