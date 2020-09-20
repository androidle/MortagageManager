package com.leevinapp.monitor.mine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.mine.databinding.MineFramentProcessedSheetsBinding

class ApplySheetProcessedFragment:BaseFragment() {

    private lateinit var viewBinding: MineFramentProcessedSheetsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return MineFramentProcessedSheetsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewBinding = this
        }.root
    }

    companion object {
        fun newInstance() : ApplySheetProcessedFragment {
            return ApplySheetProcessedFragment()
        }
    }
}