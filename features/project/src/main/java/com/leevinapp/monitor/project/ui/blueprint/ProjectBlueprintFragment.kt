package com.leevinapp.monitor.project.ui.blueprint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayoutMediator
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.core.common.ui.extensions.showErrorDialog
import com.leevinapp.monitor.core.common.view.FragmentAdapter
import com.leevinapp.monitor.core.core.utils.autoCleared
import com.leevinapp.monitor.project.R
import com.leevinapp.monitor.project.R.string
import com.leevinapp.monitor.project.databinding.ProjectFragmentBlueprintBinding
import com.leevinapp.monitor.project.domain.model.ProjectSubCategory.COLLATERAL
import com.leevinapp.monitor.project.domain.model.ProjectSubCategory.DAILY_LOG
import com.leevinapp.monitor.project.domain.model.ProjectSubCategory.DETAILS

class ProjectBlueprintFragment : BaseFragment() {

    var viewBinding by autoCleared<ProjectFragmentBlueprintBinding>()

    var currentSubCategory = DETAILS

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
        TabLayoutMediator(
            viewBinding.tabLayout, viewBinding.viewPager
        ) { tab, position ->
            tab.text = titles[position]
        }
            .attach()

        viewBinding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                currentSubCategory = when (position) {
                    0 -> DETAILS
                    1 -> COLLATERAL
                    2 -> DAILY_LOG
                    else -> DETAILS
                }
            }
        })
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
            ProjectDetailsFragment.newInstance(),
            ProjectCollateralFragment.newInstance(),
            ProjectDailyLogFragment.newInstance()
        )
    }

    override fun onRighterIconClick() {
        when (currentSubCategory) {
            DETAILS -> {
                requireActivity().showErrorDialog("删除!!! todo")
            }
            COLLATERAL -> {
            }
            DAILY_LOG -> {
            }
        }
    }

    override fun onRightestIconClick() {
        when (currentSubCategory) {
            DETAILS -> {
                findNavController().navigate(R.id.project_action_projectblueprintfragment_to_projectsendnotificationfragment)
            }
            COLLATERAL -> {
                findNavController().navigate(R.id.project_action_projectblueprintfragment_to_projectaddcollateralfragment)
            }
            DAILY_LOG -> {
                findNavController().navigate(R.id.project_action_projectblueprintfragment_to_projecttypeloginfofragment)
            }
        }
    }

    override fun getRightestIconRes(): Int {
        return R.drawable.ic_add_circle
    }

    override fun getRighterIconRes(): Int {
        return R.drawable.ic_delete
    }

    override fun getTitleBarView(): View? {
        return viewBinding.toolbarContainer.toolbar
    }

    override fun getTitleBarTitle(): String {
        // TODO: 2020/10/1 last page project name
        return getString(string.project_mine)
    }

    override fun onClickUp() {
        requireActivity().finish()
    }

    companion object {
        fun newInstance(): ProjectBlueprintFragment {
            return ProjectBlueprintFragment()
        }
    }
}
