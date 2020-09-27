package com.leevinapp.monitor.mine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.common.ui.base.ViewModelFragment
import com.leevinapp.monitor.core.common.view.recycleview.HorizontalDividerItemDecoration
import com.leevinapp.monitor.core.core.utils.autoCleared
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.databinding.MineFramentSubInstitutionsBinding
import com.leevinapp.monitor.mine.di.buildComponent
import com.leevinapp.monitor.mine.ui.adapter.SubInstitutionAdapter
import javax.inject.Inject

class SubInstitutionsFragment : ViewModelFragment() {

    private var viewBinding by autoCleared<MineFramentSubInstitutionsBinding>()

    @Inject
    lateinit var viewModel: SubInstitutionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return MineFramentSubInstitutionsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewBinding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SubInstitutionAdapter().apply {
            // updateData(getDummyData())
        }
        viewBinding.recyclerViewSubInstitution.adapter = adapter

        viewBinding.recyclerViewSubInstitution.addItemDecoration(
            HorizontalDividerItemDecoration.Builder(requireContext())
                .showLastDivider()
                .sizeResId(R.dimen.dimen_common_margin_1)
                .build()
        )

        viewModel.getSubInstitutions()

        viewModel.subInstitutionsResult.observe(viewLifecycleOwner, Observer {
            adapter.updateData(it)
        })
    }

    override fun initDependencyInjection() {
        buildComponent(this).inject(this)
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun getTitleBarView(): View? {
        return viewBinding.toolbarContainer.toolbar
    }

    override fun getTitleBarTitle(): String {
        return getString(R.string.mine_menu_sub_institutions)
    }
}
