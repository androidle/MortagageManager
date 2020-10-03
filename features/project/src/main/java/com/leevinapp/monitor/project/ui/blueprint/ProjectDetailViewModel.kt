package com.leevinapp.monitor.project.ui.blueprint

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.project.domain.ProjectRepository
import com.leevinapp.monitor.project.domain.model.ProjectModel
import javax.inject.Inject

class ProjectDetailViewModel @Inject constructor(private val repository: ProjectRepository) : BaseViewModel() {

    val projectModel: MutableLiveData<ProjectModel> by lazy {
        MutableLiveData(ProjectModel())
    }
}
