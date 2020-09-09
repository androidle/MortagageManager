package com.leevinapp.monitor.core.common.view

import android.content.Context
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import androidx.core.content.ContextCompat
import com.leevinapp.monitor.core.R

class CustomClickableSpan(private val context: Context, val clickCallback: (() -> Unit)? = null) :
    ClickableSpan() {

    override fun onClick(widget: View) {
        clickCallback?.invoke()
    }

    override fun updateDrawState(ds: TextPaint) {
        ds.setColor(ContextCompat.getColor(context, R.color.color_monitor_text_blue))
        ds.isUnderlineText = false
    }
}
