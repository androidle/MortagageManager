package com.leevinapp.monitor.project.domain.model

import com.leevinapp.monitor.project.R

enum class ProjectStatus(val desc: String, val colorId: Int = 0) {
    NORMAL("正常", R.color.color_monitor_status_normal),
    OVERDUE("逾期", R.color.color_monitor_status_overdue),
    EXTENDED("延期", R.color.color_monitor_status_extended),
    DONE("完成", R.color.color_monitor_status_done),
    OTHERS("其他", R.color.color_monitor_status_others)
}
