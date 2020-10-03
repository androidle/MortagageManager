package com.leevinapp.monitor.core.core.utils.vaidation

interface BaseRule {

    var value: String

    fun validate(value: String = this.value): Boolean

    var errorMessage: String
}
