package com.leevinapp.monitor.core.common.ui.base

import android.view.View

interface WithToolbar {
    fun getTitleBarView(): View? = null

    fun getTitleBarTitle(): String = ""

    fun isShowBackIcon(): Boolean = true

    fun isShowRightActionText(): Boolean = false

    fun getToolBarRightIconRes(): Int = 0
}
