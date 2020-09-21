package com.leevinapp.monitor.mine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.core.common.view.recycleview.HorizontalDividerItemDecoration
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.databinding.MineFramentSubInstitutionsBinding
import com.leevinapp.monitor.mine.domain.model.InstitutionModel
import com.leevinapp.monitor.mine.ui.adapter.SubInstitutionAdapter
import kotlinx.android.synthetic.main.mine_frament_sub_institutions.*

class SubInstitutionsFragment : BaseFragment() {

    private lateinit var viewBinding: MineFramentSubInstitutionsBinding

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

        recyclerView_sub_institution.adapter = SubInstitutionAdapter().apply {
            updateData(getDummyData())
        }

        recyclerView_sub_institution.addItemDecoration(
            HorizontalDividerItemDecoration.Builder(requireContext())
                .sizeResId(R.dimen.dimen_common_margin_1)
                .build()
        )
    }

    private fun getDummyData(): MutableList<InstitutionModel> {
        val dummy = mutableListOf<InstitutionModel>()
        for (i in 0..100) {
            dummy.add(
                InstitutionModel().apply {
                    institutionName = "内蒙古银行股份有限公司$i"
                    id = i.toLong()
                }
            )
        }

        return dummy
    }

    override fun getTitleBarView(): View? {
        return viewBinding.toolbarContainer.toolbar
    }

    override fun getTitleBarTitle(): String {
        return getString(R.string.mine_menu_sub_institutions)
    }
}
