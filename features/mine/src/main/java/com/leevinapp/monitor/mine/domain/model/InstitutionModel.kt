package com.leevinapp.monitor.mine.domain.model

class InstitutionModel(
    var username: String = "",
    var userrole: String = "",
    var institutionName: String = "",
    var id: Long = 0,
    var isExpand: Boolean = false
) : SearchResult {
    override fun getName(): String {
        return institutionName
    }

    override fun getValue(): String {
        return id.toString()
    }
}
