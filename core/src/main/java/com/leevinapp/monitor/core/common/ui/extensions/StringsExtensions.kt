package com.leevinapp.monitor.core.common.ui.extensions

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.annotation.ColorInt

fun String.refactorColorSpan(start: Int, end: Int, @ColorInt colorInt: Int): SpannableStringBuilder {

    val spannableStringBuilder = SpannableStringBuilder(this)
    val foregroundColorSpan = ForegroundColorSpan(colorInt)
    spannableStringBuilder.setSpan(foregroundColorSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return spannableStringBuilder
}
