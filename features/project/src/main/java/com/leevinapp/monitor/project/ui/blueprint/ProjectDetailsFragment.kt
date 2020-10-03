package com.leevinapp.monitor.project.ui.blueprint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.common.ui.base.ViewModelFragment
import com.leevinapp.monitor.core.core.utils.autoCleared
import com.leevinapp.monitor.project.databinding.ProjectFragmentDetailsBinding
import com.leevinapp.monitor.project.di.buildComponent
import javax.inject.Inject

class ProjectDetailsFragment : ViewModelFragment() {

    var viewBing by autoCleared<ProjectFragmentDetailsBinding>()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: ProjectDetailViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ProjectFragmentDetailsBinding.inflate(inflater, container, false).apply {
            viewBing = this
        }
            .root
    }

    override fun initDependencyInjection() {
        buildComponent(this).inject(this)
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    companion object {
        fun newInstance(): ProjectDetailsFragment {
            return ProjectDetailsFragment()
        }
    }
}
