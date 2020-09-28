package com.leevinapp.monitor.mine.ui

import com.leevinapp.monitor.mine.data.params.RequestTicketParams
import com.leevinapp.monitor.mine.domain.MineRepository
import com.leevinapp.monitor.mine.domain.model.TicketType.DEPEND_ON_ORG_VERIFY
import javax.inject.Inject

class ApplyAttachedInstitutionViewModel @Inject constructor(repository: MineRepository) : ApplyParentInstitutionViewModel(repository) {

    override fun requestTicketParams(): RequestTicketParams {
        return RequestTicketParams(
            desc = requestDesc.value ?: "",
            targetId = currentRequestInstitution.value?.parentId ?: 0,
            type = DEPEND_ON_ORG_VERIFY
        )
    }
}
