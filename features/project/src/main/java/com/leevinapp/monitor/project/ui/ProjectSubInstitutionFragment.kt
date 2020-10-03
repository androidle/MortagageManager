package com.leevinapp.monitor.project.ui

import com.leevinapp.monitor.project.domain.model.ProjectType.SUB_INSTITUTION_PROJECT

class ProjectSubInstitutionFragment : ProjectMineFragment() {

    override fun initProjectType() {
        viewModel.setProjectType(SUB_INSTITUTION_PROJECT)
    }

    companion object {
        fun newInstance(): ProjectSubInstitutionFragment {
            return ProjectSubInstitutionFragment()
        }
    }
}
