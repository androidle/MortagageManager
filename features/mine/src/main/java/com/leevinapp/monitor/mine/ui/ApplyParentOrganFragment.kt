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

class ApplyParentOrganFragment : ViewModelFragment()  {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: ApplyParentOrganViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var viewBinding: MineFragmentApplyParentOrganBinding

    override fun getViewModel(): BaseViewModel {
        TODO("Not yet implemented")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return MineFragmentApplyParentOrganBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@ApplyParentOrganFragment.viewModel
            viewBinding = this
        }.root
    }

    override fun getTitleBarView(): View? {
        return viewBinding.toolbarContainer.toolbar
    }

    override fun getTitleBarTitle(): String {
        return getString(R.string.mine_apply_parent_organ)
    }

    override fun initDependencyInjection() {
        buildComponent(this).inject(this)
    }
}