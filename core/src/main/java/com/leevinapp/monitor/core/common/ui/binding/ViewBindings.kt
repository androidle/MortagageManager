package com.leevinapp.monitor.core.common.ui.binding

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("app:visible")
fun visible(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("src")
fun setSrc(view: ImageView, resId: Int) {
    view.setImageResource(resId)
}

@BindingAdapter("background")
fun setBackground(view: View, id: Int) {
    view.setBackgroundResource(id)
}

@BindingAdapter("backgroundColor")
fun setBackgroundColor(view: View, id: Int) {
    view.setBackgroundColor(ContextCompat.getColor(view.context, id))
}
