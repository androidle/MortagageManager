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
import com.leevinapp.monitor.auth.di.buildComponent
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.common.ui.base.ViewModelFragment
import com.leevinapp.monitor.core.common.view.CustomClickableSpan
import com.leevinapp.monitor.core.core.user.UserManager
import com.leevinapp.monitor.core.core.utils.autoCleared
import javax.inject.Inject

class RegisterFragment : ViewModelFragment() {

    @Inject
    lateinit var userManager: UserManager

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var binding by autoCleared<AuthFragmentRegisterBinding>()

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
            binding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonRegister.setOnClickListener {
            viewModel.registerUser()
        }

        binding.ievSmsCode.setSmsCodeClickListener {
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

        binding.tvToLogin.text = spannableString
        binding.tvToLogin.movementMethod = LinkMovementMethod.getInstance()

        viewModel.registerSuccess.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(requireContext(), "注册成功", Toast.LENGTH_SHORT)
                findNavController().popBackStack(R.id.logonFragment, true)
            }
        })

        viewModel.smsCodeResult.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), if (it) "发送成功" else "发送失败", Toast.LENGTH_SHORT)
        })
    }

    override fun initDependencyInjection() {
        buildComponent(this).inject(this)
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun onStop() {
        super.onStop()
        binding.ievSmsCode.cancelTimer()
    }
}
