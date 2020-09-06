package com.leevinapp.monitor.mine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.databinding.MineFragmentPersonalInfoBinding

class MinePersonalInfoFragment : BaseFragment() {

    private lateinit var viewBinding: MineFragmentPersonalInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return MineFragmentPersonalInfoBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewBinding = this
        }.root
    }

    override fun getToolbar(): View? {
        return viewBinding.toolbarContainer.toolbar
    }

    override fun getToolbarTitle(): String {
        return getString(R.string.mine_personal_info)
    }
}
