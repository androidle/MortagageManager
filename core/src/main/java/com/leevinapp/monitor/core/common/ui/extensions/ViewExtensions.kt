package com.leevinapp.monitor.core.common.ui.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.showKeyBoard() {
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.showSoftInput(
        this,
        InputMethodManager.SHOW_IMPLICIT
    )
}

fun View.hideKeyBoard() {
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.hideSoftInputFromWindow(
        windowToken,
        0
    )
}
