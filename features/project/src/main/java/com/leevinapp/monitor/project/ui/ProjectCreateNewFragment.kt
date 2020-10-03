package com.leevinapp.monitor.project.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.common.ui.base.ViewModelFragment
import com.leevinapp.monitor.core.core.utils.autoCleared
import com.leevinapp.monitor.project.R
import com.leevinapp.monitor.project.databinding.ProjectFragmentCreateNewBinding
import com.leevinapp.monitor.project.di.buildComponent
import javax.inject.Inject

class ProjectCreateNewFragment : ViewModelFragment() {

    private var viewBinding by autoCleared<ProjectFragmentCreateNewBinding>()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: ProjectCreateNewViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ProjectFragmentCreateNewBinding.inflate(inflater, container, false).apply {
            viewBinding = this
            viewModel = this@ProjectCreateNewFragment.viewModel
        }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.buttonSubmit.setOnClickListener {
            // TODO: 2020/10/2
        }

        viewBinding.ievProjectManagerName.setOnRightIconClickListener {
            // TODO: 2020/10/2 goto search dialog fragment
        }
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun initDependencyInjection() {
        buildComponent(this).inject(this)
    }

    override fun getTitleBarTitle(): String {
        return getString(R.string.project_create_new)
    }

    override fun getTitleBarView(): View? {
        return viewBinding.toolbarContainer.toolbar
    }
}
