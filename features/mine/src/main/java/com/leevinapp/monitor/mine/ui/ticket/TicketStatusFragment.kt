package com.leevinapp.monitor.mine.ui.ticket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leevinapp.monitor.core.common.ui.base.BaseFragment
import com.leevinapp.monitor.core.common.view.recycleview.HorizontalDividerItemDecoration
import com.leevinapp.monitor.core.core.utils.autoCleared
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.databinding.MineFramentTicketsStatusBinding
import com.leevinapp.monitor.mine.domain.model.TicketModel
import com.leevinapp.monitor.mine.ui.adapter.TicketsAdapter

abstract class TicketStatusFragment : BaseFragment() {

    private var viewBinding by autoCleared<MineFramentTicketsStatusBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return MineFramentTicketsStatusBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewBinding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.recyclerViewTickets.adapter = TicketsAdapter().apply {
            updateData(getDummyData())
        }

        viewBinding.recyclerViewTickets.addItemDecoration(
            HorizontalDividerItemDecoration.Builder(requireContext())
                .sizeResId(R.dimen.dimen_common_margin_1)
                .build()
        )
    }

    abstract fun getDummyData(): MutableList<TicketModel>
}
