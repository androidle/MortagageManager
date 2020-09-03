package com.leevinapp.monitor.common

import android.content.Context
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.leevinapp.monitor.core.R
import com.leevinapp.monitor.core.common.view.recycleview.HorizontalDividerItemDecoration

object UiUtil {
    fun dpToPx(context: Context): Int {
        return 0
    }

    fun getDividerDecoration(context: Context): ItemDecoration {
        return HorizontalDividerItemDecoration.Builder(context)
            .colorResId(R.color.color_monitor_pewter)
            .sizeResId(R.dimen.dimen_divider)
            .showLastDivider()
            .build()
    }
}
