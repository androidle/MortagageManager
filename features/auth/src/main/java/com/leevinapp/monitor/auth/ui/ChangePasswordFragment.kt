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
import com.leevinapp.monitor.auth.databinding.AuthFragmentChangePasswordBinding
import com.leevinapp.monitor.auth.di.buildComponent
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.common.ui.base.ViewModelFragment
import kotlinx.android.synthetic.main.auth_fragment_change_password.*
import javax.inject.Inject

class ChangePasswordFragment : ViewModelFragment() {

    private lateinit var viewBinding: AuthFragmentChangePasswordBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: ChangePasswordViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return AuthFragmentChangePasswordBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@ChangePasswordFragment.viewModel
            viewBinding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_completed.setOnClickListener {
            viewModel.changePassword()
        }

        viewModel.changePasswordResult.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), if (it) "修改成功" else "修改失败", Toast.LENGTH_SHORT)
            if (it) {
                findNavController().navigateUp()
            }
        })
    }

    override fun getTitleBarView(): View? {
        return viewBinding.toolbarContainer.toolbar
    }

    override fun getTitleBarTitle(): String {
        return getString(R.string.auth_change_password)
    }

    override fun initDependencyInjection() {
        buildComponent(this).inject(this)
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }
}
