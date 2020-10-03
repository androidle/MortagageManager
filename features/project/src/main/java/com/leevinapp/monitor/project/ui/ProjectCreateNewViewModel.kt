package com.leevinapp.monitor.project.ui

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.project.domain.ProjectRepository
import javax.inject.Inject

class ProjectCreateNewViewModel @Inject constructor(private val repository: ProjectRepository) :
    BaseViewModel() {

    val projectName = MutableLiveData("")
    val projectManagerName = MutableLiveData("")
    val phoneNumber = MutableLiveData("")
    val jobPosition = MutableLiveData("")
}
