package com.leevinapp.monitor.mine.ui.ticket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.common.ui.base.ViewModelFragment
import com.leevinapp.monitor.core.common.view.recycleview.HorizontalDividerItemDecoration
import com.leevinapp.monitor.core.core.utils.autoCleared
import com.leevinapp.monitor.mine.R
import com.leevinapp.monitor.mine.databinding.MineFramentTicketsStatusBinding
import com.leevinapp.monitor.mine.di.buildComponent
import com.leevinapp.monitor.mine.ui.adapter.TicketsAdapter
import javax.inject.Inject

abstract class TicketStatusFragment : ViewModelFragment() {

    var viewBinding by autoCleared<MineFramentTicketsStatusBinding>()

    @Inject
    lateinit var viewModel: TicketViewModel

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
        val ticketsAdapter = TicketsAdapter()
        viewBinding.recyclerViewTickets.adapter = ticketsAdapter.apply {
            setApproveListener {
                viewModel.approveTicket(it)
            }
            setRejectCallback {
                viewModel.rejectTicket(it)
            }
        }

        viewBinding.recyclerViewTickets.addItemDecoration(
            HorizontalDividerItemDecoration.Builder(requireContext())
                .sizeResId(R.dimen.dimen_common_margin_1)
                .build()
        )

        viewModel.getTickets(getStatus())

        viewModel.ticketsResult.observe(viewLifecycleOwner, Observer {
            ticketsAdapter.updateData(it)
        })
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun initDependencyInjection() {
        buildComponent(this).inject(this)
    }

    abstract fun getStatus(): String
}
