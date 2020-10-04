package com.leevinapp.monitor.project.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.common.ui.base.ViewModelFragment
import com.leevinapp.monitor.core.core.utils.UiUtil
import com.leevinapp.monitor.core.core.utils.autoCleared
import com.leevinapp.monitor.project.R
import com.leevinapp.monitor.project.databinding.ProjectFragmentProjectBinding
import com.leevinapp.monitor.project.di.buildComponent
import com.leevinapp.monitor.project.domain.model.ProjectType.MINE_PROJECT
import com.leevinapp.monitor.project.ui.adapter.ProjectAdapter
import javax.inject.Inject

open class ProjectMineFragment : ViewModelFragment() {

    var viewBinding by autoCleared<ProjectFragmentProjectBinding>()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: ProjectViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ProjectFragmentProjectBinding.inflate(inflater, container, false).apply {
            viewModel = this@ProjectMineFragment.viewModel
            viewBinding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initProjectType()
        val projectAdapter = ProjectAdapter {
            findNavController().navigate(R.id.project_action_projectfragment_to_projectblueprintfragment)
        }
        with(viewBinding.recyclerView) {
            adapter = projectAdapter
            addItemDecoration(
                UiUtil.getDividerDecoration(
                    requireContext()
                )
            )
        }

        viewModel.projects.observe(viewLifecycleOwner, Observer {
            projectAdapter.updateData(it)
        })

        viewModel.getProjects()
    }

    open fun initProjectType() {
        viewModel.setProjectType(MINE_PROJECT)
    }

    override fun initDependencyInjection() {
        buildComponent(this).inject(this)
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    companion object {
        fun newInstance(): ProjectMineFragment {
            return ProjectMineFragment()
        }
    }
}
