package com.leevinapp.monitor.core.core.network.mock

import com.leevinapp.monitor.core.core.CommonTestUtil.getJsonString
import com.leevinapp.monitor.core.core.network.ApiResponse
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ConvertUtilTest {

    private lateinit var convertUtil: ConvertUtil

    @Before
    fun setUp() {
        convertUtil = ConvertUtil()
    }

    @Test
    fun fromJsonObject() {
        val jsonString = getJsonString("normal_object.json")
        val user: User = convertUtil.fromJsonObject(jsonString, User::class.java)
        Assert.assertEquals(user.name, "Hello")
    }

    @Test
    fun fromJsonArray() {
        val jsonString = getJsonString("normal_array.json")
        val users: List<User> = convertUtil.fromJsonArray(jsonString, User::class.java)
        Assert.assertTrue(users.isNotEmpty())
    }

    @Test
    fun fromJsonObjectToApiResponse() {
        val jsonString = getJsonString("api_response_object.json")
        val response = convertUtil.fromJsonObjectToApiResponse(jsonString, User::class.java)
        Assert.assertEquals(response.data.name, "Hello")
    }

    @Test
    fun fromJsonArrayToApiResponse() {
        val jsonString = getJsonString("api_response_array.json")
        val response: ApiResponse<List<User>> = convertUtil.fromJsonArrayToApiResponse(jsonString, User::class.java)
        Assert.assertTrue(response.data.isNotEmpty())
    }
}

data class User(
    val name: String,
    val id: Int
)
