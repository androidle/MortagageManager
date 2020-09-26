package com.leevinapp.monitor.mine.ui.ticket

import com.leevinapp.monitor.mine.domain.model.TicketModel
import com.leevinapp.monitor.mine.domain.model.TicketStatus.AUDITING
import java.util.Calendar

class TicketOfPendingFragment : TicketStatusFragment() {

    override fun getDummyData(): MutableList<TicketModel> {
        val dummyTickets = mutableListOf<TicketModel>()
        for (i in 0..50) {
            val tickets = TicketModel().apply {
                titleName = "申请成本机构用户"
                createDate = Calendar.getInstance().time
                applicant = "王小华"
                phoneNumber = "18712345678"
                realname = "王小华"
                identityType = "金融机构普通用户"
                identityNumber = "6105001234567892"
                homeAddress = "内蒙古呼伦贝尔市海拉尔"
                organName = "内蒙古银行股份有限公司"
                socialCode = "91150700561228427B"
                jobPosition = "财务"
                status = AUDITING
            }
            dummyTickets.add(tickets)
        }
        return dummyTickets
    }

    override fun getStatus(): String {
        return "in_progress"
    }

    companion object {
        fun newInstance(): TicketOfPendingFragment {
            return TicketOfPendingFragment()
        }
    }
}
