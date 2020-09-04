package com.leevinapp.monitor.mine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leevinapp.monitor.common.UiUtil
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.domain.model.MenuModel.PRIVACY
import com.leevinapp.monitor.mine.domain.model.MenuModel.SERVICE
import com.leevinapp.monitor.mine.domain.model.MenuModel.VERSION
import com.leevinapp.monitor.mine.ui.adapter.MineMenuAdapter
import kotlinx.android.synthetic.main.mine_frament_about.*

class MineAboutFragment : BaseFragment() {

    private val menus = mutableListOf(
        SERVICE,
        PRIVACY,
        VERSION
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.mine_frament_about, container, false)
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
        return getString(R.string.mine_about)
    }
}
