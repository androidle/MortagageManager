package com.leevinapp.monitor.mine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.core.common.view.recycleview.HorizontalDividerItemDecoration
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.databinding.MineFramentNotificationsBinding
import com.leevinapp.monitor.mine.domain.model.NotificationModel
import com.leevinapp.monitor.mine.ui.adapter.NotificationAdapter
import kotlinx.android.synthetic.main.mine_frament_notifications.*

class NotificationsFragment : BaseFragment() {

    private lateinit var viewBinding: MineFramentNotificationsBinding

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

        recyclerView_notifications.adapter = NotificationAdapter {
            findNavController().navigate(
                NotificationsFragmentDirections.mineActionMineNotificationsfragmentToMineNotificationdetailsfragment(
                    it
                )
            )
        }.apply {
            updateData(getDummyData())
        }
        recyclerView_notifications.setHasFixedSize(true)
        recyclerView_notifications.addItemDecoration(
            HorizontalDividerItemDecoration.Builder(requireContext())
                .sizeResId(R.dimen.dimen_common_margin_1)
                .build()
        )
    }

    private fun getDummyData(): MutableList<NotificationModel> {
        val dummy = mutableListOf<NotificationModel>()
        for (i in 0..15) {
            dummy.add(
                NotificationModel().apply {
                    title = "申请机构用户名"
                    date = "2020-03-05"
                    applicant = "Leevin"
                    phoneNumber = "18712345678"
                    isRead = i % 3 == 0
                }
            )
        }

        return dummy
    }

    override fun getTitleBarView(): View? {
        return viewBinding.toolbarContainer.toolbar
    }

    override fun getTitleBarTitle(): String {
        return getString(R.string.mine_menu_notification)
    }
}
