package com.jeff.pizza.base

import android.content.SharedPreferences
import androidx.test.rule.ActivityTestRule
import com.jeff.pizza.MainActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule


open class BaseFragmentTest {

    @Rule(order = 1)
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    lateinit var mockServer: MockWebServer

    @Before
    open fun setUp() {
        mockServer = MockWebServer()
        mockServer.start(8080)
    }

    @After
    fun tearDown() = mockServer.shutdown()

    fun SharedPreferences.clear() {
        edit().apply {
            clear()
            commit()
        }
    }
}