package com.jeff.pizza.pageobject

import com.jeff.pizza.R
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed


class SplashPageObject {

    fun assertVisible() {
        assertDisplayed(R.id.imageSplash)
    }
}