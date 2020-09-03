package com.leevinapp.monitor.core.common.ui.binding

import android.view.View
import androidx.databinding.BindingAdapter

@set:BindingAdapter("visible")
var View.visible
    get() = visibility == View.VISIBLE
    set(value) {
        visibility == if (value) View.VISIBLE else View.GONE
    }

@set:BindingAdapter("gone")
var View.gone
    get() = visibility == View.GONE
    set(value) {
        visibility == if (value) View.GONE else View.VISIBLE
    }

@set:BindingAdapter("invisible")
var View.invisible
    get() = visibility == View.GONE
    set(value) {
        visibility == if (value) View.INVISIBLE else View.VISIBLE
    }
