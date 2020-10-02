package com.leevinapp.monitor.auth.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.leevinapp.monitor.auth.R
import com.leevinapp.monitor.auth.databinding.AuthFragmentForgotPasswordBinding
import com.leevinapp.monitor.auth.di.buildComponent
import com.leevinapp.monitor.auth.domain.model.ResetPasswordType.EMAIL
import com.leevinapp.monitor.auth.domain.model.ResetPasswordType.SMS
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.core.core.user.UserManager
import com.leevinapp.monitor.core.core.utils.autoCleared
import javax.inject.Inject
import kotlinx.android.synthetic.main.auth_fragment_forgot_password.*

class ForgotPasswordFragment : BaseFragment() {

    private var viewBinding by autoCleared<AuthFragmentForgotPasswordBinding>()

    @Inject
    lateinit var userManager: UserManager

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: ResetPasswordViewModel by activityViewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return AuthFragmentForgotPasswordBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@ForgotPasswordFragment.viewModel
            viewBinding = this
        }.root
    }

    override fun initDependencyInjection() {
        buildComponent(this).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.tvChangeEmail.setOnClickListener {
            container_email.visibility = View.VISIBLE
            container_sms_code.visibility = View.GONE
            viewModel.resetType = EMAIL
        }

        viewBinding.tvChangeSmsCode.setOnClickListener {
            container_email.visibility = View.GONE
            container_sms_code.visibility = View.VISIBLE
            viewModel.resetType = SMS
        }

        viewBinding.ievSmsCode.setSmsCodeClickListener {
            viewModel.sendSmsCode()
        }
        viewBinding.ievEmailVerifyCode.setSmsCodeClickListener {
            viewModel.sendEmailCode()
        }

        viewModel.smsCodeResult.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), if (it) "发送成功" else "发送失败", Toast.LENGTH_SHORT)
        })

        viewModel.emailCodeResult.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), if (it) "发送成功" else "发送失败", Toast.LENGTH_SHORT)
        })
    }

    override fun getTitleBarView(): View? {
        return viewBinding.toolbarContainer.toolbar
    }

    override fun getTitleBarTitle(): String {
        return getString(R.string.auth_forgot_password)
    }

    override fun isShowRightActionText(): Boolean {
        return true
    }

    override fun onRightTextClick() {
        if (viewModel.validate()) {
            findNavController().navigate(R.id.auth_action_auth_forgotpasswordfragment_to_resetpasswordfragment)
        }
    }

    override fun onStop() {
        super.onStop()
        viewBinding.ievSmsCode.cancelTimer()
        viewBinding.ievEmailVerifyCode.cancelTimer()
    }
}
