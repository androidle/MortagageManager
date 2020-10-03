package com.leevinapp.monitor.core.core.utils

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
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

    val Int.px: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()

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

    fun renderRoundDrawable(@Dimension cornerSize: Int, @ColorInt color: Int): Drawable {
        val shapeAppearanceModel = ShapeAppearanceModel()
            .toBuilder()
            .setAllCorners(CornerFamily.ROUNDED, cornerSize.toFloat())
            .build()

        val shapeDrawable = MaterialShapeDrawable(shapeAppearanceModel)

        val colorStateList = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_enabled)
            ),
            intArrayOf(
                color
            )
        )

        shapeDrawable.fillColor = colorStateList
        return shapeDrawable
    }
}
