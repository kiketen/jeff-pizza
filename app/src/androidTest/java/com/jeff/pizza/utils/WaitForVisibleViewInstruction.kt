package com.jeff.pizza.utils

import androidx.test.espresso.AmbiguousViewMatcherException
import androidx.test.espresso.Espresso
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import junit.framework.AssertionFailedError

class WaitForVisibleViewInstruction(val id: Int): Instruction() {

    override val description: String
        get() = "View must be visible"

    override fun checkCondition(): Boolean {
        return try {
            Espresso.onView(ViewMatchers.withId(id)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            true
        } catch (e: AssertionFailedError) {
            false
        } catch (e: NoMatchingViewException) {
            false
        } catch (e: AmbiguousViewMatcherException) {
            true
        }
    }
}