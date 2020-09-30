package com.leevinapp.monitor.core.common.ui.dialog

import android.view.View
import com.leevinapp.monitor.core.R
import com.leevinapp.monitor.core.databinding.FragmentErrorDialogBinding

class ErrorDialogFragment : BaseDialogFragment<FragmentErrorDialogBinding>() {

    private var message: String? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_error_dialog
    }

    override fun initView(view: View) {
        super.initView(view)
        viewBinding.tvMessage.text = message
        viewBinding.buttonPositive.setOnClickListener {
            dismissAllowingStateLoss()
        }
    }

    fun errorMessage(message: String?) {
        this.message = message
    }

    companion object {
        fun newInstance(): ErrorDialogFragment {
            return ErrorDialogFragment()
        }
    }
}
