package com.leevinapp.monitor.core.common.ui.extensions

import androidx.fragment.app.FragmentActivity
import com.leevinapp.monitor.core.common.ui.dialog.ErrorDialogFragment
import com.leevinapp.monitor.core.common.ui.dialog.LoadingDialogFragment

fun FragmentActivity.hideLoadingDialog() {
    supportFragmentManager.findFragmentByTag(LoadingDialogFragment::class.simpleName)?.let {
        supportFragmentManager.beginTransaction()
            .remove(it)
            .commitAllowingStateLoss()
    }
}

fun FragmentActivity.showLoadingDialog() {
    supportFragmentManager.beginTransaction()
        .add(LoadingDialogFragment(), LoadingDialogFragment::class.simpleName)
        .commitAllowingStateLoss()
}

fun FragmentActivity.hideErrorDialog() {
    supportFragmentManager.findFragmentByTag(ErrorDialogFragment::class.simpleName)?.let {
        supportFragmentManager.beginTransaction()
            .remove(it)
            .commitAllowingStateLoss()
    }
}

fun FragmentActivity.showErrorDialog(errorMessage: String? = "络网错误") {
    supportFragmentManager.beginTransaction()
        .add(ErrorDialogFragment.newInstance(errorMessage), LoadingDialogFragment::class.simpleName)
        .commitAllowingStateLoss()
}
