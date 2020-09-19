package com.leevinapp.monitor.mine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.common.ui.base.ViewModelFragment
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.databinding.MineFragmentApplyParentOrganBinding
import com.leevinapp.monitor.mine.di.buildComponent
import javax.inject.Inject

class ApplyParentInstitutionFragment : ViewModelFragment()  {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: ApplyParentInstitutionViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var viewBinding: MineFragmentApplyParentOrganBinding

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return MineFragmentApplyParentOrganBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@ApplyParentInstitutionFragment.viewModel
            viewBinding = this
        }.root
    }

    override fun getTitleBarView(): View? {
        return viewBinding.toolbarContainer.toolbar
    }

    override fun getTitleBarTitle(): String {
        return getString(R.string.mine_apply_parent_institution)
    }

    override fun initDependencyInjection() {
        buildComponent(this).inject(this)
    }
}