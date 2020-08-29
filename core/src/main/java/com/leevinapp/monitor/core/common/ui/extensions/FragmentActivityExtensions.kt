package com.leevinapp.monitor.core.common.ui.extensions

import androidx.fragment.app.FragmentActivity
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
