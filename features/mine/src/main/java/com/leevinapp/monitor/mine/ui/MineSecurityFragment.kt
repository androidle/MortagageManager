package com.leevinapp.monitor.mine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leevinapp.monitor.common.UiUtil
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.R.layout
import com.leevinapp.monitor.mine.domain.model.MenuModel.CHANGE_PASSWORD
import com.leevinapp.monitor.mine.domain.model.MenuModel.CHANGE_SECURITY_REF
import com.leevinapp.monitor.mine.domain.model.MenuModel.FORGOT_PASSWORD
import com.leevinapp.monitor.mine.ui.adapter.MineMenuAdapter
import kotlinx.android.synthetic.main.mine_fragment_security.*

class MineSecurityFragment : BaseFragment() {

    private val menus = mutableListOf(
        CHANGE_PASSWORD,
        FORGOT_PASSWORD,
        CHANGE_SECURITY_REF
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout.mine_fragment_security, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = MineMenuAdapter(menus) {
            // TODO: 2020/9/3
        }
        recycler_view.addItemDecoration(
            UiUtil.getDividerDecoration(requireContext())
        )
    }

    override fun getToolbar(): View? {
        return toolbar_container
    }

    override fun getToolbarTitle(): String {
        return getString(R.string.mine_security)
    }
}
