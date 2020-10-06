package com.leevinapp.monitor.project.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.core.core.utils.autoCleared
import com.leevinapp.monitor.project.databinding.ProjectFragmentTypeLogInfoBinding
import com.leevinapp.monitor.project.ui.blueprint.ProjectDailyLogFragment

class ProjectTypeLogInfoFragment : BaseFragment() {

    var viewBinging by autoCleared<ProjectFragmentTypeLogInfoBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ProjectFragmentTypeLogInfoBinding.inflate(inflater, container, false).apply {
            viewBinging = this
        }
            .root
    }

    override fun getTitleBarView(): View? {
        return viewBinging.toolbarContainer.toolbar
    }

    override fun getTitleBarTitle(): String {
        return "录入台账信息"
    }

    companion object {
        fun newInstance(): ProjectDailyLogFragment {
            return ProjectDailyLogFragment()
        }
    }
}
