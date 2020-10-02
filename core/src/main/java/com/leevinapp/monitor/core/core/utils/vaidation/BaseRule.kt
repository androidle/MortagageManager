package com.leevinapp.monitor.core.core.utils.vaidation

interface BaseRule {

    fun validate(value: String): Boolean

    var errorMessage: String
}
