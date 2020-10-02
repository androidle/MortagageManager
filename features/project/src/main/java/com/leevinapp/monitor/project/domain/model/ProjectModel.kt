package com.leevinapp.monitor.project.domain.model

import com.leevinapp.monitor.core.core.utils.toYearMonthDay
import com.leevinapp.monitor.project.domain.model.ProjectStatus.OTHER
import java.util.Date

data class ProjectModel(
    var name: String = "",
    var createDate: Date? = null,
    var managerName: String = "",
    var status: ProjectStatus = OTHER,
    var subInstitutionName: String = ""
) {

    fun convertLocalDate(): String {
        return createDate?.toYearMonthDay() ?: ""
    }
}
