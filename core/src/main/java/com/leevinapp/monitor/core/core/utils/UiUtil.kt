package com.leevinapp.monitor.common

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.leevinapp.monitor.core.R
import com.leevinapp.monitor.core.common.view.recycleview.HorizontalDividerItemDecoration

object UiUtil {

    fun getDividerDecoration(context: Context): ItemDecoration {
        return HorizontalDividerItemDecoration.Builder(context)
            .colorResId(R.color.color_monitor_pearl)
            .sizeResId(R.dimen.dimen_divider)
            .showLastDivider()
            .build()
    }

    fun getScreenSize(context: Context): IntArray {
        val displayMetrics = context.resources.displayMetrics
        return intArrayOf(displayMetrics.widthPixels, displayMetrics.heightPixels)
    }

    val Float.dp: Float
        get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics
        )

    val Int.dp: Int
        get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
        ).toInt()

    val Float.sp: Float
        get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, this, Resources.getSystem().displayMetrics
        )

    val Int.sp: Int
        get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
        ).toInt()
}
