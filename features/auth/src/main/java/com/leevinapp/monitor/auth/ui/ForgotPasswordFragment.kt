package com.leevinapp.monitor.auth.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.leevinapp.monitor.auth.R
import com.leevinapp.monitor.auth.databinding.AuthFragmentForgotPasswordBinding
import com.leevinapp.monitor.core.common.ui.base.BaseFragment

class ForgotPasswordFragment : BaseFragment() {

    private lateinit var viewBinding: AuthFragmentForgotPasswordBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return AuthFragmentForgotPasswordBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewBinding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        findNavController().navigate(R.id.auth_action_auth_forgotpasswordfragment_to_resetpasswordfragment)
    }
}
