package com.jeff.pizza.pageobject

import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.jeff.pizza.base.withRecyclerView
import com.jeff.pizza.utils.ConditionWatcher
import com.jeff.pizza.utils.WaitForVisibleViewInstruction
import org.hamcrest.Matcher


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

    fun assertTextVisible(text: String) {
        Espresso.onView(ViewMatchers.withText(text))
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    fun assertRecyclerViewItemChild(@IdRes recyclerViewId: Int, @IdRes childIdRes: Int, position: Int, matcherView: Matcher<View>) {
        Espresso.onView(withRecyclerView(recyclerViewId).atPositionOnView(position, childIdRes)).check(ViewAssertions.matches(matcherView))
    }
}