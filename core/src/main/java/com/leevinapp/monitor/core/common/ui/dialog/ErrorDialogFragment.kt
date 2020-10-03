package com.leevinapp.monitor.core.common.ui.dialog

import android.view.View
import com.leevinapp.monitor.core.R
import com.leevinapp.monitor.core.databinding.FragmentDialogErrorBinding

class ErrorDialogFragment : BaseDialogFragment<FragmentDialogErrorBinding>() {

    private var message: String? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_dialog_error
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
