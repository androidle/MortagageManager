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
import com.leevinapp.monitor.auth.databinding.AuthFragmentResetPasswordBinding
import com.leevinapp.monitor.auth.di.buildComponent
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.common.ui.base.ViewModelFragment
import com.leevinapp.monitor.core.core.utils.autoCleared
import javax.inject.Inject

class ResetPasswordFragment : ViewModelFragment() {

    private var viewBinding by autoCleared<AuthFragmentResetPasswordBinding>()

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
        return AuthFragmentResetPasswordBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@ResetPasswordFragment.viewModel
            viewBinding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.buttonCompleted.setOnClickListener {
            viewModel.resetPassword()
        }

        viewModel.resetPasswordResultResult.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(requireContext(), "密码已重置", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack(R.id.logonFragment, false)
                viewModel.resetPasswordResultResult.postValue(false)
            }
        })
    }

    override fun getTitleBarView(): View? {
        return viewBinding.toolbarContainer.toolbar
    }

    override fun getTitleBarTitle(): String {
        return getString(R.string.auth_forgot_password)
    }

    override fun initDependencyInjection() {
        buildComponent(this).inject(this)
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }
}
