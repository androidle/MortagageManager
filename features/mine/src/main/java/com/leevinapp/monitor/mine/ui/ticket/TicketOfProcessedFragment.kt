package com.leevinapp.monitor.mine.ui.ticket

class TicketOfProcessedFragment : TicketStatusFragment() {

    override fun getStatus(): String {
        return "finished"
    }

    companion object {
        fun newInstance(): TicketOfProcessedFragment {
            return TicketOfProcessedFragment()
        }
    }
}
