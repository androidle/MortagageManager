package com.leevinapp.monitor.mine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.common.ui.base.ViewModelFragment
import com.leevinapp.monitor.core.common.view.recycleview.HorizontalDividerItemDecoration
import com.leevinapp.monitor.core.core.utils.autoCleared
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.databinding.MineFramentNotificationsBinding
import com.leevinapp.monitor.mine.di.buildComponent
import com.leevinapp.monitor.mine.ui.adapter.NotificationAdapter
import javax.inject.Inject
import kotlinx.android.synthetic.main.mine_frament_notifications.*

class NotificationsFragment : ViewModelFragment() {

    private var viewBinding by autoCleared<MineFramentNotificationsBinding>()

    @Inject
    lateinit var viewModel: NotificationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return MineFramentNotificationsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewBinding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val notificationAdapter = NotificationAdapter {
            viewModel.stopFresh()
            findNavController().navigate(
                NotificationsFragmentDirections.mineActionMineNotificationsfragmentToMineNotificationdetailsfragment(
                    it
                )
            )
        }
        recyclerView_notifications.adapter = notificationAdapter

        recyclerView_notifications.setHasFixedSize(true)
        recyclerView_notifications.addItemDecoration(
            HorizontalDividerItemDecoration.Builder(requireContext())
                .sizeResId(R.dimen.dimen_common_margin_1)
                .build()
        )

        viewModel.getNotifications()

        viewModel.notificationsResult.observe(viewLifecycleOwner, Observer {
            notificationAdapter.updateData(it)
        })
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
        return getString(R.string.mine_menu_notification)
    }
}
