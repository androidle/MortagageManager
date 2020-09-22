package com.leevinapp.monitor.core.common.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.leevinapp.monitor.core.core.utils.autoCleared
import com.leevinapp.monitor.core.databinding.FragmentLoadingDialogBinding

class LoadingDialogFragment : DialogFragment() {

    private var viewBinding by autoCleared<FragmentLoadingDialogBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false
        return FragmentLoadingDialogBinding.inflate(inflater, container, false).apply {
            viewBinding = this
        }
            .root
    }
}
