package com.leevinapp.monitor.project.data

import com.leevinapp.monitor.core.core.network.exception.ResponseException
import com.leevinapp.monitor.project.data.api.ProjectService
import com.leevinapp.monitor.project.domain.ProjectRepository
import com.leevinapp.monitor.project.domain.model.ProjectModel
import com.leevinapp.monitor.project.domain.model.ProjectType
import io.reactivex.Single

class ProjectRepositoryImpl(private val projectService: ProjectService) : ProjectRepository {

    override fun getProjects(projectType: ProjectType?): Single<MutableList<ProjectModel>> {
        return projectService.getProjects(projectType)
            .map { it ->
                if (!it.success) {
                    throw ResponseException.resultException(it.error)
                }
                val resultList = mutableListOf<ProjectModel>()
                it.data.forEach {
                    resultList.add(it.toModel())
                }

                resultList
            }
    }
}
