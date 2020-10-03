package com.leevinapp.monitor.project.ui.blueprint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.core.common.view.FragmentAdapter
import com.leevinapp.monitor.core.core.utils.autoCleared
import com.leevinapp.monitor.project.R.string
import com.leevinapp.monitor.project.databinding.ProjectFragmentBlueprintBinding

class ProjectBlueprintFragment : BaseFragment() {

    var viewBinding by autoCleared<ProjectFragmentBlueprintBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ProjectFragmentBlueprintBinding.inflate(inflater, container, false).apply {
            viewBinding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titles = getTitles()
        viewBinding.viewPager.adapter = FragmentAdapter(this, getFragments())
        TabLayoutMediator(viewBinding.tabLayout, viewBinding.viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = titles[position]
            })
            .attach()
    }

    private fun getTitles(): MutableList<String> {
        return mutableListOf(
            getString(string.project_detail_info),
            getString(string.project_pawn),
            getString(string.project_account_info)
        )
    }

    private fun getFragments(): MutableList<Fragment> {
        return mutableListOf(
            ProjectInfoFragment.newInstance(),
            ProjectPawnFragment.newInstance(),
            ProjectAccountInfoFragment.newInstance()
        )
    }

    override fun getTitleBarView(): View? {
        return viewBinding.toolbarContainer.toolbar
    }

    override fun getTitleBarTitle(): String {
        // TODO: 2020/10/1 last page project name
        return getString(string.project_mine)
    }
}
