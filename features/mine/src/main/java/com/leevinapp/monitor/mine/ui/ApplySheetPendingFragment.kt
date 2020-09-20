package com.leevinapp.monitor.mine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.mine.databinding.MineFramentPendingSheetsBinding

class ApplySheetPendingFragment : BaseFragment() {

    private lateinit var viewBinding: MineFramentPendingSheetsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return MineFramentPendingSheetsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewBinding = this
        }.root
    }

    companion object {
        fun newInstance(): ApplySheetPendingFragment {
            return ApplySheetPendingFragment()
        }
    }
}
