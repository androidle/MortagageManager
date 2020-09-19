package com.leevinapp.monitor.mine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.core.common.view.recycleview.HorizontalDividerItemDecoration
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.databinding.MineFramentInstitutionUserBinding
import com.leevinapp.monitor.mine.domain.model.InstitutionModel
import com.leevinapp.monitor.mine.ui.adapter.InstitutionUserAdapter
import kotlinx.android.synthetic.main.mine_frament_institution_user.*

class InstitutionUserFragment : BaseFragment() {

    private lateinit var viewBinding:MineFramentInstitutionUserBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return MineFramentInstitutionUserBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewBinding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView_institution_user.adapter = InstitutionUserAdapter().apply {
            updateData(getDummyData())
        }

        recyclerView_institution_user.addItemDecoration(
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
                    username = "王小华$i"
                    userrole = "财务专员"
                    id = i.toString()
                }
            )
        }

        return dummy
    }

    override fun getTitleBarView(): View? {
        return viewBinding.toolbarContainer.toolbar
    }

    override fun getTitleBarTitle(): String {
        return getString(R.string.mine_menu_institution_user)
    }
}
