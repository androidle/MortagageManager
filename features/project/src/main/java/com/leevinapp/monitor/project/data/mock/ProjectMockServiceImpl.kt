package com.leevinapp.monitor.project.data.mock

import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.core.core.network.mock.MockApiUtil
import com.leevinapp.monitor.project.data.api.ProjectService
import com.leevinapp.monitor.project.data.response.GetProjectsResponse
import com.leevinapp.monitor.project.domain.model.ProjectStatus.NORMAL
import com.leevinapp.monitor.project.domain.model.ProjectType
import com.leevinapp.monitor.project.domain.model.ProjectType.MINE_PROJECT
import com.leevinapp.monitor.project.domain.model.ProjectType.SUB_INSTITUTION_PROJECT
import io.reactivex.Single
import java.util.Calendar

class ProjectMockServiceImpl constructor(private val mockApiUtil: MockApiUtil) : ProjectService {

    override fun getProjects(projectType: ProjectType?): Single<ApiResponse<MutableList<GetProjectsResponse>>> {
        return Single.just(getDummyResponse(projectType))
    }

    private fun getDummyResponse(projectType: ProjectType?): ApiResponse<MutableList<GetProjectsResponse>> {
        val data = mutableListOf<GetProjectsResponse>()
        for (i in 0..100) {
            data.add(
                GetProjectsResponse(
                    id = i.toLong(),
                    name = "项目$i",
                    createDate = Calendar.getInstance().time,
                    subInstitutionName = if (projectType == SUB_INSTITUTION_PROJECT) "长安银行" else "",
                    managerName = if (projectType == MINE_PROJECT) "李小明" else "",
                    status = NORMAL
                )
            )
        }

        return ApiResponse(true, "", data)
    }
}
