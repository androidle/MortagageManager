package com.leevinapp.monitor.mine.ui.ticket

class TicketOfPendingFragment : TicketStatusFragment() {

    override fun getStatus(): String {
        return "in_progress"
    }

    companion object {
        fun newInstance(): TicketOfPendingFragment {
            return TicketOfPendingFragment()
        }
    }

}
