package com.leevinapp.monitor.core.core.utils.vaidation

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class EmailRuleTest {

    private lateinit var emailRule: EmailRule

    @Before
    fun setUp() {
        emailRule = EmailRule()
    }

    @Test
    fun validate_success() {
        Assert.assertTrue(emailRule.validate("123@163.com"))
    }

    @Test
    fun validate_failure() {
        Assert.assertFalse(emailRule.validate("."))
        Assert.assertFalse(emailRule.validate(".com"))
        Assert.assertFalse(emailRule.validate("163.com"))
    }

    @Test
    fun getErrorMessage() {
        Assert.assertEquals(emailRule.errorMessage, EMAIL_INVALID_MESSAGE)
    }

    @Test
    fun setErrorMessage() {
        emailRule.errorMessage = "email错误"
        Assert.assertEquals(emailRule.errorMessage, "email错误")
    }
}
