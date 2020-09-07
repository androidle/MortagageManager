package com.leevinapp.monitor.core.common.ui.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.leevinapp.monitor.core.common.ui.dialog.ErrorDialogFragment
import com.leevinapp.monitor.core.common.ui.dialog.LoadingDialogFragment

abstract class ViewModelFragment : BaseFragment() {

    private var loadingDialogFragment = LoadingDialogFragment()
    private var errorDialogFragment = ErrorDialogFragment.newInstance("")

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
            if (it != null) {
                showErrorDialogFragment(it)
            }
        })
    }

    private fun showErrorDialogFragment(it: String?) {
        if (errorDialogFragment.isAdded.not()) {
            errorDialogFragment.show(childFragmentManager,ErrorDialogFragment::class.simpleName)
        }
    }

    abstract fun getViewModel(): BaseViewModel
}
