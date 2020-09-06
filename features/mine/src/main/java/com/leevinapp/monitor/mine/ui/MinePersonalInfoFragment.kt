package com.leevinapp.monitor.mine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.databinding.MineFragmentPersonalInfoBinding
import com.leevinapp.monitor.mine.di.buildComponent
import javax.inject.Inject

class MinePersonalInfoFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: PersonalInfoViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var viewBinding: MineFragmentPersonalInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return MineFragmentPersonalInfoBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@MinePersonalInfoFragment.viewModel
            viewBinding = this
        }.root
    }

    override fun getToolbar(): View? {
        return viewBinding.toolbarContainer.toolbar
    }

    override fun getToolbarTitle(): String {
        return getString(R.string.mine_personal_info)
    }

    override fun initDependencyInjection() {
        buildComponent(this).inject(this)
    }
}
