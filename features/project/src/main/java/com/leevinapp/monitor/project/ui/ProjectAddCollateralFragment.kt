package com.leevinapp.monitor.project.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.core.core.utils.autoCleared
import com.leevinapp.monitor.project.databinding.ProjectFragmentAddCallateralBinding
import com.leevinapp.monitor.project.ui.blueprint.ProjectDailyLogFragment

class ProjectAddCollateralFragment : BaseFragment() {

    var viewBinging by autoCleared<ProjectFragmentAddCallateralBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ProjectFragmentAddCallateralBinding.inflate(inflater, container, false).apply {
            viewBinging = this
        }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getTitleBarView(): View? {
        return viewBinging.toolbarContainer.toolbar
    }

    override fun getTitleBarTitle(): String {
        return "添加质押物"
    }

    companion object {
        fun newInstance(): ProjectDailyLogFragment {
            return ProjectDailyLogFragment()
        }
    }
}
