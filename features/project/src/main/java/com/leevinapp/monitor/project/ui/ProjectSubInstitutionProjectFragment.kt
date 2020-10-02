package com.leevinapp.monitor.project.ui

import com.leevinapp.monitor.project.domain.model.ProjectType.SUB_INSTITUTION_PROJECT

class ProjectSubInstitutionProjectFragment : ProjectMineProjectFragment() {

    override fun initProjectType() {
        viewModel.setProjectType(SUB_INSTITUTION_PROJECT)
    }

    companion object {
        fun newInstance(): ProjectSubInstitutionProjectFragment {
            return ProjectSubInstitutionProjectFragment()
        }
    }
}
