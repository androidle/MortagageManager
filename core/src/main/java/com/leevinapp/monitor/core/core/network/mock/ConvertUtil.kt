package com.leevinapp.monitor.core.core.network.mock

import com.google.gson.Gson
import com.leevinapp.monitor.core.core.network.ApiResponse
import java.lang.reflect.Type

class ConvertUtil {

    private val gson = Gson()

    fun <T> fromJsonObject(json: String, clazz: Class<T>): T {
        return gson.fromJson(json, clazz)
    }

    fun <T> fromJsonArray(json: String, clazz: Class<T>): List<T> {
        val listType: Type = ParameterizedTypeImpl(MutableList::class.java, arrayOf(clazz))
        return gson.fromJson(json, listType)
    }

    fun <T> fromJsonObjectToApiResponse(json: String, clazz: Class<T>): ApiResponse<T> {
        val type: Type = ParameterizedTypeImpl(ApiResponse::class.java, arrayOf(clazz))
        return gson.fromJson(json, type)
    }

    fun <T> fromJsonArrayToApiResponse(json: String, clazz: Class<T>): ApiResponse<List<T>> {
        val listType: Type = ParameterizedTypeImpl(MutableList::class.java, arrayOf(clazz))
        val type: Type = ParameterizedTypeImpl(ApiResponse::class.java, arrayOf(listType))
        return gson.fromJson(json, type)
    }
}
