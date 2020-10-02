package com.leevinapp.monitor.project.data.response

import com.leevinapp.monitor.project.domain.model.ProjectModel
import com.leevinapp.monitor.project.domain.model.ProjectStatus
import com.leevinapp.monitor.project.domain.model.ProjectStatus.OTHER
import java.util.Date

data class GetProjectsResponse(
    var id: Long,
    var name: String? = null,
    var createDate: Date? = null,
    var managerName: String? = null,
    var status: ProjectStatus? = null,
    var subInstitutionName: String? = null
) {

    fun toModel(): ProjectModel {
        return ProjectModel().apply {
            name = this@GetProjectsResponse.name ?: ""
            createDate = this@GetProjectsResponse.createDate
            managerName = this@GetProjectsResponse.managerName ?: ""
            status = this@GetProjectsResponse.status ?: OTHER
            subInstitutionName = this@GetProjectsResponse.subInstitutionName ?: ""
        }
    }
}
