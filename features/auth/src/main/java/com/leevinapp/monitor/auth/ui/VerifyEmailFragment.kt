package com.leevinapp.monitor.auth.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.leevinapp.monitor.auth.R
import com.leevinapp.monitor.auth.databinding.AuthFragmentVerifyEmailBinding
import com.leevinapp.monitor.auth.di.buildComponent
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.common.ui.base.ViewModelFragment
import com.leevinapp.monitor.core.core.utils.autoCleared
import javax.inject.Inject

class VerifyEmailFragment : ViewModelFragment() {

    private var viewBinding by autoCleared<AuthFragmentVerifyEmailBinding>()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: VerifyEmailViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return AuthFragmentVerifyEmailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@VerifyEmailFragment.viewModel
            viewBinding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.ievEmailVerifyCode.setSmsCodeClickListener {
            viewModel.sendEmailCode()
        }

        viewModel.emailCodeResult.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), if (it) "发送成功" else "发送失败", Toast.LENGTH_SHORT)
        })

        viewBinding.buttonCompleted.setOnClickListener {
            viewModel.verifyEmail()
        }

        viewModel.verifyEmailResult.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), if (it) "验证成功" else "验证失败", Toast.LENGTH_SHORT)
            if (it) {
                findNavController().navigateUp()
            }
        })
    }

    override fun getTitleBarView(): View? {
        return viewBinding.toolbarContainer.toolbar
    }

    override fun getTitleBarTitle(): String {
        return getString(R.string.auth_verify_email)
    }

    override fun initDependencyInjection() {
        buildComponent(this).inject(this)
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun onStop() {
        super.onStop()
        viewBinding.ievEmailVerifyCode.cancelTimer()
    }
}
