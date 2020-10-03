package com.leevinapp.monitor.core.core.utils.vaidation

class Validator() {

    private var rules = mutableListOf<BaseRule>()

    var errorMessage: String = ""

    var errorCallback: ((message: String) -> Unit)? = null
    var successCallback: (() -> Unit)? = null

    fun check(): Boolean {
        for (rule in rules) {
            if (!rule.validate()) {
                errorMessage = rule.errorMessage
                errorCallback?.invoke(errorMessage)
                return false
            }
        }
        successCallback?.invoke()
        return true
    }

    fun addSuccessCallback(callback: () -> Unit): Validator {
        this.successCallback = callback
        return this
    }

    fun addErrorCallback(callback: (message: String) -> Unit): Validator {
        this.errorCallback = callback
        return this
    }

    fun addRule(vararg rules: BaseRule): Validator {
        this.rules.addAll(rules)
        return this
    }

    fun reset(): Validator {
        rules.clear()
        errorCallback = null
        successCallback = null
        return this
    }
}
