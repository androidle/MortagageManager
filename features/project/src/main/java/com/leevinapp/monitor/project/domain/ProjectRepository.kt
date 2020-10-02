package com.leevinapp.monitor.project.domain

import com.leevinapp.monitor.project.domain.model.ProjectModel
import com.leevinapp.monitor.project.domain.model.ProjectType
import io.reactivex.Single

interface ProjectRepository {
    fun getProjects(projectType: ProjectType?): Single<MutableList<ProjectModel>>
}
