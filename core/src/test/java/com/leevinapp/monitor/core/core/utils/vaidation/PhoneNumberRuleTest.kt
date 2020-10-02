package com.leevinapp.monitor.core.core.utils.vaidation

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PhoneNumberRuleTest {

    private lateinit var phoneNumberRule: PhoneNumberRule

    @Before
    fun setUp() {
        phoneNumberRule = PhoneNumberRule()
    }

    @Test
    fun validate_success() {
        Assert.assertTrue(phoneNumberRule.validate("13468918761"))
    }

    @Test
    fun validate_failure() {
        Assert.assertFalse(phoneNumberRule.validate(""))
        Assert.assertFalse(phoneNumberRule.validate("2434"))
        Assert.assertFalse(phoneNumberRule.validate("1122334455667788"))
    }

    @Test
    fun getErrorMessage() {
        Assert.assertEquals(phoneNumberRule.errorMessage, PHONE_NUMBER_INVALID_MESSAGE)
    }

    @Test
    fun setErrorMessage() {
        phoneNumberRule.errorMessage = "手机号错误"
        Assert.assertEquals(phoneNumberRule.errorMessage, "手机号错误")
    }
}
