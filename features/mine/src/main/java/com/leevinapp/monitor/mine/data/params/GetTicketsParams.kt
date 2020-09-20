package com.leevinapp.monitor.mine.data.params

data class GetTicketsParams(
    val current: Int,
    val pageSize: Int,
    val sorter: Sorter,
    val status: String,
    val type: String,
    val usernameKeyword: String
)

class Sorter()
