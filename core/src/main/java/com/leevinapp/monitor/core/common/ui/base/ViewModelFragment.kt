package com.leevinapp.monitor.core.common.ui.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.leevinapp.monitor.core.common.ui.dialog.LoadingDialogFragment
import com.leevinapp.monitor.core.common.ui.extensions.showErrorDialog

abstract class ViewModelFragment : BaseFragment() {

    private var loadingDialogFragment = LoadingDialogFragment()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerCommonEvents()
    }

    private fun registerCommonEvents() {
        getViewModel().loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                loadingDialogFragment.show(
                    childFragmentManager,
                    LoadingDialogFragment::class.simpleName
                )
            } else if (loadingDialogFragment.isAdded) {
                loadingDialogFragment.dismissAllowingStateLoss()
            }
        })

        getViewModel().errorMessage.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty().not()) {
                requireActivity().showErrorDialog(it)
            }
        })
    }

    abstract fun getViewModel(): BaseViewModel
}
