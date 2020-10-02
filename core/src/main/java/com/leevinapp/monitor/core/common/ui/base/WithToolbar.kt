package com.leevinapp.monitor.core.common.ui.base

import android.view.View
import androidx.annotation.DrawableRes

interface WithToolbar {
    fun getTitleBarView(): View? = null

    fun getTitleBarTitle(): String = ""

    fun isShowBackIcon(): Boolean = true

    fun isShowRightActionText(): Boolean = false

    fun getRightText(): String = ""
    @DrawableRes
    fun getRighterIconRes(): Int = 0
    @DrawableRes
    fun getRightestIconRes(): Int = 0
}
