package com.leevinapp.monitor.project.ui.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.use
import androidx.databinding.BindingAdapter
import com.leevinapp.monitor.core.core.utils.UiUtil
import com.leevinapp.monitor.project.R
import kotlinx.android.synthetic.main.project_view_statue.view.*

class StatusView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var count: Int
        get() {
            return try {
                Integer.parseInt(tv_count.text.toString())
            } catch (e: Exception) {
                0
            }
        }
        set(value) {
            if (value < 0) {
                tv_count.visibility = View.GONE
            } else {
                tv_count.visibility = View.VISIBLE
                if (value == 0) {
                    tv_count.text = ""
                } else {
                    tv_count.text = value.toString()
                }
            }
        }

    var desc: String?
        get() {
            return tv_desc.text.toString()
        }
        set(value) {
            tv_desc.text = value
        }

    fun setStatusColor(@ColorInt color: Int) {
        tv_count.setBackgroundColor(color)
    }

    fun setDescVisibility(showDesc: Boolean) {
        if (showDesc) tv_desc.visibility = View.VISIBLE else View.GONE
    }

    init {
        View.inflate(context, R.layout.project_view_statue, this)

        attrs?.let {
            context.obtainStyledAttributes(attrs, R.styleable.project_StatusView)
                .use { typedArray ->
                    for (i in 0 until typedArray.indexCount) {
                        when (val attr = typedArray.getIndex(i)) {
                            R.styleable.project_StatusView_project_count -> {
                                count = typedArray.getInt(attr, 0)
                            }
                            R.styleable.project_StatusView_project_desc -> {
                                desc = typedArray.getString(attr)
                            }
                            R.styleable.project_StatusView_project_color -> {
                                var colorInt = typedArray.getColor(attr, Color.TRANSPARENT)
                                setStatusColor(colorInt)
                            }
                            R.styleable.project_StatusView_project_isShowDesc -> {
                                val isShowDesc = typedArray.getBoolean(attr, true)
                                setDescVisibility(isShowDesc)
                            }
                        }
                    }
                }
        }
    }

    companion object {
        @BindingAdapter("statusCount")
        @JvmStatic
        fun setStatusCount(statusView: StatusView, statusCount: Int) {
            statusView.count = statusCount
        }

        @BindingAdapter("statusColor")
        @JvmStatic
        fun setStatusColor(statusView: StatusView, @ColorRes colorId: Int) {
            val color = ContextCompat.getColor(statusView.context, colorId)
            val cornerSize =
                statusView.context.resources.getDimensionPixelSize(R.dimen.dimen_circle)
            statusView.tv_count.background = UiUtil.renderRoundDrawable(cornerSize, color)
        }

        @BindingAdapter("statusDesc")
        @JvmStatic
        fun setStatusDesc(statusView: StatusView, desc: String) {
            statusView.desc = desc
        }
    }
}
