package com.leevinapp.monitor.mine.ui.generalInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leevinapp.monitor.core.BuildConfig
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.core.core.utils.UiUtil
import com.leevinapp.monitor.core.core.utils.autoCleared
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.databinding.MineFragmentAboutBinding
import com.leevinapp.monitor.mine.domain.model.MenuModel
import com.leevinapp.monitor.mine.domain.model.MenuModel.PRIVACY
import com.leevinapp.monitor.mine.domain.model.MenuModel.SERVICE
import com.leevinapp.monitor.mine.domain.model.MenuModel.VERSION
import com.leevinapp.monitor.mine.ui.adapter.MineMenuAdapter

class MineAboutFragment : BaseFragment() {

    var viewBinding by autoCleared<MineFragmentAboutBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return MineFragmentAboutBinding.inflate(inflater, container, false).apply {
            viewBinding = this
        }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.recyclerView.setHasFixedSize(true)
        viewBinding.recyclerView.adapter = MineMenuAdapter(getMenus()) {
            // TODO: 2020/9/3
        }

        viewBinding.recyclerView.addItemDecoration(
            UiUtil.getDividerDecoration(requireContext())
        )

        viewBinding.tvVersion.text = getString(R.string.mine_version, BuildConfig.VERSION_NAME)
    }

    private fun getMenus(): MutableList<MenuModel> {
        return mutableListOf(
            SERVICE,
            PRIVACY,
            VERSION
        )
    }

    override fun getTitleBarView(): View? {
        return viewBinding.toolbarContainer.toolbar
    }

    override fun getTitleBarTitle(): String {
        return getString(R.string.mine_about)
    }
}
