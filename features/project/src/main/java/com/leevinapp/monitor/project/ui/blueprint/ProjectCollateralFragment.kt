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
import com.leevinapp.monitor.project.databinding.ProjectFragmentCollateralBinding
import com.leevinapp.monitor.project.di.buildComponent
import javax.inject.Inject

class ProjectCollateralFragment : ViewModelFragment() {

    var viewBinging by autoCleared<ProjectFragmentCollateralBinding>()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: ProjectCollateralViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ProjectFragmentCollateralBinding.inflate(inflater, container, false).apply {
            viewBinging = this
        }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initDependencyInjection() {
        buildComponent(this).inject(this)
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    companion object {
        fun newInstance(): ProjectCollateralFragment {
            return ProjectCollateralFragment()
        }
    }
}
