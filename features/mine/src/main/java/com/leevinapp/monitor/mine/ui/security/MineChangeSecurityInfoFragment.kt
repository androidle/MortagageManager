package com.leevinapp.monitor.mine.ui.security

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.mine.R
import kotlinx.android.synthetic.main.mine_fragment_change_security_info.*

class MineChangeSecurityInfoFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.mine_fragment_change_security_info, container, false)
    }

    override fun getTitleBarView(): View? {
        return toolbar_container
    }

    override fun getTitleBarTitle(): String {
        return getString(R.string.mine_change_security_info)
    }
}
