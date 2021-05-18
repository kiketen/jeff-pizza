package com.jeff.pizza.pageobject

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.jeff.pizza.utils.ConditionWatcher
import com.jeff.pizza.utils.WaitForVisibleViewInstruction


interface PageObject {

    fun waitForVisible(@IdRes id: Int) {
        ConditionWatcher.waitForCondition(
                WaitForVisibleViewInstruction(id)
        )
    }

    fun assertTextVisible(@StringRes text: Int) {
        Espresso.onView(ViewMatchers.withText(text))
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }
}