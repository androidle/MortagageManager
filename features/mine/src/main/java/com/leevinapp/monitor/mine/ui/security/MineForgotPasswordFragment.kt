package com.leevinapp.monitor.mine.ui.security

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.mine.R
import kotlinx.android.synthetic.main.mine_fragment_forgot_password.*

class MineForgotPasswordFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.mine_fragment_forgot_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_next.setOnClickListener {
            findNavController().navigate(MineForgotPasswordFragmentDirections.mineActionMineMineforgotpasswordfragmentToMineresetpasswordfragment())
        }
    }
    override fun getToolbar(): View? {
        return toolbar_container
    }

    override fun getToolbarTitle(): String {
        return getString(R.string.mine_forgot_password)
    }
}
