package com.leevinapp.monitor.core.core.utils.vaidation

import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ValidatorTest {

    private lateinit var validator: Validator

    @Before
    fun setUp() {
        validator = Validator()
    }

    @Test
    fun addSuccessCallback() {
        val callback = {}
        validator.addSuccessCallback(callback)
        Assert.assertEquals(validator.successCallback, callback)
    }

    @Test
    fun addErrorCallback() {
        val callback = { _: String -> }
        validator.addErrorCallback(callback)
        Assert.assertEquals(validator.errorCallback, callback)
    }

    @Test
    fun withoutRuleCheckReturnTrue() {
        Assert.assertTrue(validator.check())
    }

    @Test
    fun checkReturnFalse_WhenRuleValidateFalse() {
        val callback: (String) -> Unit = spyk()

        val rule: BaseRule = mockk(relaxed = true) {
            every { validate(any()) } returns false
            every { errorMessage } returns "message"
        }

        validator.addRule(rule).addErrorCallback(callback)
        Assert.assertFalse(validator.check())

        verify { callback.invoke("message") }
    }

    @Test
    fun checkReturnFalse_WhenAnyRuleValidateFalse() {
        val callback: (String) -> Unit = spyk()

        val rule1: BaseRule = mockk(relaxed = true) {
            every { validate(any()) } returns true
            every { errorMessage } returns "message"
        }

        val rule2: BaseRule = mockk(relaxed = true) {
            every { validate(any()) } returns false
            every { errorMessage } returns "message1"
        }

        validator.addRule(rule1, rule2).addErrorCallback(callback)
        Assert.assertFalse(validator.check())

        verify { callback.invoke("message1") }
    }

    @Test
    fun checkReturnTrue_WhenAllRulesValidateTrue() {
        val errorCallback: (String) -> Unit = spyk()
        val successCallback: () -> Unit = spyk()

        val rule1: BaseRule = mockk(relaxed = true) {
            every { validate(any()) } returns true
            every { errorMessage } returns "message"
        }

        val rule2: BaseRule = mockk(relaxed = true) {
            every { validate(any()) } returns true
            every { errorMessage } returns "message"
        }

        validator
            .addRule(rule1, rule2)
            .addErrorCallback(errorCallback)
            .addSuccessCallback(successCallback)
        Assert.assertTrue(validator.check())

        verify(exactly = 1) { successCallback.invoke() }
    }
}
