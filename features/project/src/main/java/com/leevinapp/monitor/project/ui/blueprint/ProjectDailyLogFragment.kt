package com.leevinapp.monitor.project.ui.blueprint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.core.core.utils.autoCleared
import com.leevinapp.monitor.project.databinding.ProjectFragmentDailyLogBinding

class ProjectDailyLogFragment : BaseFragment() {

    var viewBinging by autoCleared<ProjectFragmentDailyLogBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ProjectFragmentDailyLogBinding.inflate(inflater, container, false).apply {
            viewBinging = this
        }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): ProjectDailyLogFragment {
            return ProjectDailyLogFragment()
        }
    }
}
