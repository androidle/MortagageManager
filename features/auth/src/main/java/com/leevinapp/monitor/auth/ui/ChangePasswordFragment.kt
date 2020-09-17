package com.leevinapp.monitor.auth.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.leevinapp.monitor.auth.R
import com.leevinapp.monitor.auth.databinding.AuthFragmentChangePasswordBinding
import com.leevinapp.monitor.auth.di.buildComponent
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.core.core.user.UserManager
import javax.inject.Inject
import kotlinx.android.synthetic.main.auth_fragment_change_password.*

class ChangePasswordFragment : BaseFragment() {

    private lateinit var viewBinding: AuthFragmentChangePasswordBinding

    @Inject
    lateinit var userManager: UserManager

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
            if (it) {
                Toast.makeText(requireContext(), if (it) "修改成功" else "修改失败", Toast.LENGTH_SHORT)
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
}
