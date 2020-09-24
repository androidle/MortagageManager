package com.leevinapp.monitor.mine.ui.ticket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.common.ui.base.ViewModelFragment
import com.leevinapp.monitor.core.core.utils.autoCleared
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.databinding.MineFragmentTicketApplyBinding
import com.leevinapp.monitor.mine.di.buildComponent
import javax.inject.Inject

class TicketApplyFragment : ViewModelFragment() {

    private var viewBinding by autoCleared<MineFragmentTicketApplyBinding>()

    private var titles = mutableListOf("待处理", "已处理")

    @Inject
    lateinit var viewModel: TicketViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return MineFragmentTicketApplyBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewBinding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentList = mutableListOf<Fragment>(
            TicketOfPendingFragment.newInstance(),
            TicketOfProcessedFragment.newInstance()
        )
        viewBinding.viewPager.adapter = object : FragmentStateAdapter(requireActivity()) {
            override fun getItemCount(): Int {
                return fragmentList.size
            }

            override fun createFragment(position: Int): Fragment {
                return fragmentList[position]
            }
        }

        TabLayoutMediator(viewBinding.tabLayout, viewBinding.viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = titles[position]
            })
            .attach()

        viewModel.getTickets()
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun initDependencyInjection() {
        buildComponent(this).inject(this)
    }

    override fun getTitleBarView(): View? {
        return viewBinding.toolbarContainer.toolbar
    }

    override fun getTitleBarTitle(): String {
        return getString(R.string.mine_menu_apply_sheets)
    }
}
