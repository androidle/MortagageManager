package com.leevinapp.monitor.project.ui

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.project.domain.ProjectRepository
import com.leevinapp.monitor.project.domain.model.ProjectModel
import com.leevinapp.monitor.project.domain.model.ProjectNumModel
import com.leevinapp.monitor.project.domain.model.ProjectType
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject
import timber.log.Timber

class ProjectViewModel @Inject constructor(private val repository: ProjectRepository) : BaseViewModel() {

    init {
        Timber.d("=========ProjectViewModel====init()=========")
    }

    val projectType = MutableLiveData(ProjectType.MINE_PROJECT)

    val projects: MutableLiveData<MutableList<ProjectModel>> by lazy {
        MutableLiveData(mutableListOf<ProjectModel>())
    }

    val projectNumberInfo: MutableLiveData<ProjectNumModel> by lazy {
        MutableLiveData(ProjectNumModel())
    }

    fun getProjects() {
        repository.getProjects(projectType.value)
            .applyIoWithLoading()
            .subscribe({ response ->
                projects.value = response
            }, {})
            .addTo(compositeDisposable)
    }

    fun setProjectType(projectType: ProjectType) {
        this.projectType.value = projectType
    }
}
