package com.leevinapp.monitor.core

import io.mockk.every
import io.mockk.spyk
import io.mockk.verify
import io.mockk.verifySequence
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_car() {
        val mock = spyk<Car>(recordPrivateCalls = true)

        every { mock["accelerate"]() } returns "going not so fast"

        assertEquals("going not so fast", mock.drive())

        verifySequence {
            mock.drive()
            mock["accelerate"]()
        }
    }

    @Test
    fun test_car1() {
        val mock = spyk<Car1>(recordPrivateCalls = true)

        every { mock.accelerate() } returns "going not so fast"

        assertEquals("going not so fast", mock.drive())

        verifySequence {
            mock.drive()
            mock.accelerate()
        }
    }

    @Test
    fun test_car1_method() {
        // val mock = spyk<Car1>()
        // every { mock.methodA() } just Runs
        // every { mock.methodB() } just Runs
        // every { mock.methodC() } just Runs
        //
        // mock.methodA()
        //
        // val mockk = mockk<Car1>(relaxUnitFun = true)
        // every { mockk.methodB() } just Runs
        //
        // mockk.methodA()
        // verify {
        //     mockk.methodA()
        //     mockk.methodB()
        //     // mockk.methodC()
        // }
    }

    @Test
    fun test_car1_method_() {
        val mock = spyk<Car1>()
        // real method
        mock.methodA()

        verify {
            mock.methodA()
            mock.methodB()
            mock.methodC()
        }
    }
}
