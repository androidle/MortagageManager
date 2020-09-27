package com.leevinapp.monitor.mine.data.params

import com.leevinapp.monitor.mine.domain.model.TicketType

data class RequestTicketParams(
    var desc: String,
    var targetId: Long,
    var type: TicketType
)
