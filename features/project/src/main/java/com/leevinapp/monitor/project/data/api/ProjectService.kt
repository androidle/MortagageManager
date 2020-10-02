package com.leevinapp.monitor.project.data.api

import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.project.data.response.GetProjectsResponse
import com.leevinapp.monitor.project.domain.model.ProjectType
import io.reactivex.Single
import retrofit2.http.GET

interface ProjectService {

    @GET("app/project/projects")
    fun getProjects(projectType: ProjectType?): Single<ApiResponse<MutableList<GetProjectsResponse>>>
}
