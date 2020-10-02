package com.leevinapp.monitor.project.ui

import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.project.domain.ProjectRepository
import javax.inject.Inject

class ProjectCreateNewViewModel @Inject constructor(private val repository: ProjectRepository) :
    BaseViewModel()
