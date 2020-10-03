package com.leevinapp.monitor.core.common.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.leevinapp.monitor.core.core.utils.autoCleared
import com.leevinapp.monitor.core.databinding.FragmentDialogLoadingBinding

class LoadingDialogFragment : DialogFragment() {

    private var viewBinding by autoCleared<FragmentDialogLoadingBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false
        return FragmentDialogLoadingBinding.inflate(inflater, container, false).apply {
            viewBinding = this
        }
            .root
    }
}
