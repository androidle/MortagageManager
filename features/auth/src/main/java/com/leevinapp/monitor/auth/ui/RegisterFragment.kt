package com.leevinapp.monitor.auth.ui

import android.os.Bundle
import android.os.CountDownTimer
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
import com.leevinapp.monitor.auth.databinding.AuthFragmentRegisterBinding
import com.leevinapp.monitor.auth.di.AuthModule
import com.leevinapp.monitor.auth.di.DaggerAuthComponent
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.core.common.ui.extensions.hideLoadingDialog
import com.leevinapp.monitor.core.common.ui.extensions.showLoadingDialog
import com.leevinapp.monitor.core.common.view.CustomClickableSpan
import com.leevinapp.monitor.core.core.di.CoreInjectHelper
import com.leevinapp.monitor.core.core.user.UserManager
import kotlinx.android.synthetic.main.auth_fragment_register.*
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_register.setOnClickListener {
            viewModel.registerUser()
        }

        button_sms_code.setOnClickListener {
            countDownTimer.start()
            viewModel.sendSmsCode()
        }

        val spannableString = SpannableString(getString(R.string.auth_registered_to_login))
        spannableString.setSpan(
            CustomClickableSpan(requireContext()) {
                findNavController().popBackStack(R.id.logonFragment, true)
            },
            spannableString.length - 2,
            spannableString.length,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tv_to_login.text = spannableString
        tv_to_login.movementMethod = LinkMovementMethod.getInstance()
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                requireActivity().showLoadingDialog()
            } else {
                requireActivity().hideLoadingDialog()
            }
        })

        viewModel.registerSuccessToken.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                userManager.token = it
                findNavController().popBackStack(R.id.logonFragment, true)
            }
        })
    }

    override fun initDependencyInjection() {
        DaggerAuthComponent.builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(requireContext()))
            .authModule(AuthModule(this))
            .build()
            .inject(this)
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
