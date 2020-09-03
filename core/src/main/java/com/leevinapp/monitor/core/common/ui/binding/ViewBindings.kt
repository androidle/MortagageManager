package com.leevinapp.monitor.core.common.ui.binding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("app:visible")
fun visible(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}
