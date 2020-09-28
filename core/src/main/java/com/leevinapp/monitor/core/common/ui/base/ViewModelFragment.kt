package com.leevinapp.monitor.core.common.ui.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.leevinapp.monitor.core.common.ui.extensions.hideLoadingDialog
import com.leevinapp.monitor.core.common.ui.extensions.showErrorDialog
import com.leevinapp.monitor.core.common.ui.extensions.showLoadingDialog

abstract class ViewModelFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerCommonEvents()
    }

    private fun registerCommonEvents() {
        getViewModel().loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                requireActivity().showLoadingDialog()
            } else {
                requireActivity().hideLoadingDialog()
            }
        })

        getViewModel().errorMessage.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty().not()) {
                requireActivity().showErrorDialog(it)
                getViewModel().errorMessage.postValue(null)
            }
        })
    }

    abstract fun getViewModel(): BaseViewModel
}
