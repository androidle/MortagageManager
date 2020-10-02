package com.leevinapp.monitor.project.domain.model

enum class ProjectStatus(val desc: String) {
    NORMAL("正常"),
    POSTPONE("延期"),
    OVERDUE("逾期"),
    COMPLETED("完成"),
    OTHER("其他")
}
