package com.leevinapp.monitor.core

class Car1 {
    fun drive() = accelerate()

    fun accelerate() = "going faster"

    fun methodA() {
        methodB()
        methodC()
    }

    fun methodB() {
    }

    fun methodC() {
    }
}
