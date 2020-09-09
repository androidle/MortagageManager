package com.leevinapp.monitor.core.common.view

import android.content.Context
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.use
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
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

    var isEditable: Boolean
        get() {
            return et_value.isEnabled
        }
        set(value) {
            et_value.isEnabled = value
        }

    var isIncludeSmsCode: Boolean
        get() {
            return button_sms_code.visibility == View.VISIBLE
        }
        set(value) {
            button_sms_code.visibility = if (value) View.VISIBLE else View.GONE
        }

    val countDownTimer: CountDownTimer by lazy {
        object : CountDownTimer(60 * 1000, 1000) {
            override fun onFinish() {
                button_sms_code.text = "重新获取"
                button_sms_code.isEnabled = true
            }

            override fun onTick(millisUntilFinished: Long) {
                button_sms_code.text = "${millisUntilFinished / 1000}s"
                button_sms_code.isEnabled = false
            }
        }
    }

    var smsClickCallback: (() -> Unit)? = null

    fun setSmsCodeClickListener(smsClickCallback: (() -> Unit)? = null) {
        this.smsClickCallback = smsClickCallback
    }

    fun startTimer() {
        countDownTimer.start()
    }

    fun cancelTimer() {
        countDownTimer.cancel()
    }

    fun editHint(hint: String?) {
        et_value.hint = hint
    }

    fun setInputType(type: Int) {
        et_value.inputType = type
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
                            et_value.setText(typedArray.getString(attr))
                        }
                        R.styleable.ItemEditView_editHint -> {
                            editHint(typedArray.getString(attr))
                        }
                        R.styleable.ItemEditView_isEditable -> {
                            isEditable = typedArray.getBoolean(attr, true)
                        }
                        R.styleable.ItemEditView_android_inputType -> {
                            val inputType: Int = typedArray.getInt(attr, EditorInfo.TYPE_NULL)
                            if (inputType != EditorInfo.TYPE_NULL) {
                                setInputType(inputType)
                            }
                        }
                        R.styleable.ItemEditView_isIncludeSmsCode -> {
                            isIncludeSmsCode = typedArray.getBoolean(attr, false)
                        }
                    }
                }
            }
        }

        button_sms_code.setOnClickListener {
            smsClickCallback?.invoke()
        }
    }

    companion object {

        @BindingAdapter("editValueAttrChanged")
        @JvmStatic
        fun setEditValueAttrChanged(view: ItemEditView, listener: InverseBindingListener) {
            view.et_value.addTextChangedListener(object : TextWatcher {
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

        @BindingAdapter("editValue")
        @JvmStatic
        fun setEditValue(itemEditView: ItemEditView, value: String?) {
            if (value != itemEditView.et_value.text.toString()) {
                itemEditView.et_value.setText(value)
            }
        }

        @InverseBindingAdapter(attribute = "editValue")
        @JvmStatic
        fun getEditValue(itemEditView: ItemEditView): String? {
            return itemEditView.et_value.text.toString()
        }
    }
}
