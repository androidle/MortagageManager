package com.leevinapp.monitor.core.core.network.mock

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ParameterizedTypeImpl(private val raw: Class<*>, private val args: Array<Type>) :
    ParameterizedType {
    override fun getActualTypeArguments(): Array<Type> {
        return args
    }

    override fun getRawType(): Type {
        return raw
    }

    override fun getOwnerType(): Type? {
        return null
    }
}
