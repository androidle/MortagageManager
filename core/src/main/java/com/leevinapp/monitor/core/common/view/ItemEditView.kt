package com.leevinapp.monitor.core.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.use
import com.leevinapp.monitor.core.R
import kotlinx.android.synthetic.main.item_edit_view.view.*

class ItemEditView @JvmOverloads constructor(
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

    var editValue: String?
        get() {
            return et_value.text.toString()
        }
        set(value) {
            et_value.setText(value)
        }

    var isEditable: Boolean
        get() {
            return et_value.isEnabled
        }
        set(value) {
            et_value.isEnabled = value
        }

    fun editHint(hint: String?) {
        et_value.hint = hint
    }

    init {
        View.inflate(context, R.layout.item_edit_view, this)
        attrs?.let {
            context.obtainStyledAttributes(attrs, R.styleable.ItemEditView).use { typedArray ->
                for (i in 0 until typedArray.indexCount) {
                    when (val attr = typedArray.getIndex(i)) {
                        R.styleable.ItemEditView_name -> {
                            name = typedArray.getString(attr)
                        }
                        R.styleable.ItemEditView_editValue -> {
                            editValue = typedArray.getString(attr)
                        }
                        R.styleable.ItemEditView_editHint -> {
                            editHint(typedArray.getString(attr))
                        }
                        R.styleable.ItemEditView_isEditable -> {
                            isEditable = typedArray.getBoolean(attr, true)
                        }
                    }
                }
            }
        }
    }
}
