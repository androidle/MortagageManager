package com.leevinapp.monitor.core.common.ui.base

import android.view.View

interface WithToolbar {
    fun getToolbar(): View? = null

    fun getToolbarTitle(): String = ""

    fun isShowBackIcon(): Boolean = true

    fun isShowRightIcon(): Boolean = false

    fun getToolBarRightIconRes(): Int = 0
}
