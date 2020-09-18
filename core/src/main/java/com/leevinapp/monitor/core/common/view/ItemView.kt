package com.leevinapp.monitor.core.common.view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.use
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.leevinapp.monitor.core.R
import kotlinx.android.synthetic.main.item_view.view.*

class ItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var name: String?
        get() {
            return tv_name.text.toString()
        }
        set(value) {
            tv_name.text = value
        }

    var value: String?
        get() {
            return tv_value.text.toString()
        }
        set(value) {
            tv_value.text = value
        }

    fun setArrowVisibility(isShow: Boolean) {
        iv_right.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    init {
        View.inflate(context, R.layout.item_view, this)
        attrs?.let {
                context.obtainStyledAttributes(attrs, R.styleable.ItemView).use { typedArray ->
                    for (i in 0 until typedArray.indexCount) {
                        when (val attr = typedArray.getIndex(i)) {
                            R.styleable.ItemView_label -> {
                                name = typedArray.getString(attr)
                            }
                            R.styleable.ItemView_value -> {
                                value = typedArray.getString(attr)
                            }
                            R.styleable.ItemView_hasArrow -> {
                                setArrowVisibility(typedArray.getBoolean(attr, true))
                            }
                        }
                    }
                }
        }
    }

    companion object {

        @BindingAdapter("valueAttrChanged")
        @JvmStatic
        fun setEditValueAttrChanged(view: ItemView, listener: InverseBindingListener) {
            view.tv_value.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    listener.onChange()
                }
            })
        }

        @BindingAdapter("value")
        @JvmStatic
        fun setEditValue(itemEditView: ItemView, value: String?) {
            if (value != itemEditView.tv_value.text.toString()) {
                itemEditView.tv_value.text = value
            }
        }

        @InverseBindingAdapter(attribute = "value")
        @JvmStatic
        fun getEditValue(itemEditView: ItemView): String? {
            return itemEditView.tv_value.text.toString()
        }
    }
}
