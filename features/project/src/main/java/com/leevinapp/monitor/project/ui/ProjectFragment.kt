package com.leevinapp.monitor.project.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.core.common.view.FragmentAdapter
import com.leevinapp.monitor.core.core.user.UserManager
import com.leevinapp.monitor.core.core.utils.autoCleared
import com.leevinapp.monitor.project.R
import com.leevinapp.monitor.project.R.string
import com.leevinapp.monitor.project.databinding.ProjectFragmentBinding
import com.leevinapp.monitor.project.di.buildComponent
import javax.inject.Inject

class ProjectFragment : BaseFragment() {

    var viewBinding by autoCleared<ProjectFragmentBinding>()

    @Inject
    lateinit var userManager: UserManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ProjectFragmentBinding.inflate(inflater, container, false).apply {
            viewBinding = this
            userManager = this@ProjectFragment.userManager
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (userManager.isLogged) {
            initViewPager()
        }
    }

    private fun initViewPager() {
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
            getString(string.project_mine),
            getString(string.project_sub_intitution)
        )
    }

    private fun getFragments(): MutableList<Fragment> {
        return mutableListOf(
            ProjectMineProjectFragment.newInstance(),
            ProjectSubInstitutionProjectFragment.newInstance()
        )
    }

    override fun initDependencyInjection() {
        buildComponent(this).inject(this)
    }

    override fun getTitleBarView(): View? {
        return viewBinding.toolbarContainer.toolbar
    }

    override fun getTitleBarTitle(): String {
        return getString(string.project_mine)
    }

    override fun isShowBackIcon(): Boolean {
        return false
    }

    override fun getRightestIconRes(): Int {
        return if (userManager.isLogged) R.drawable.ic_add_circle else 0
    }

    override fun getRighterIconRes(): Int {
        return if (userManager.isLogged) R.drawable.ic_search else 0
    }

    override fun onRighterIconClick() {
        findNavController().navigate(R.id.project_action_projectfragment_to_projectblueprintfragment)
    }

    override fun onRightestIconClick() {
        findNavController().navigate(R.id.project_action_projectfragment_to_projectcreatenewfragment)
    }
}
