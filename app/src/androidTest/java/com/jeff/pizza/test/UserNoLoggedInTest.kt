package com.jeff.pizza.test

import android.content.SharedPreferences
import com.jeff.pizza.base.BaseFragmentTest
import com.jeff.pizza.di.ApplicationModule
import com.jeff.pizza.login.R
import com.jeff.pizza.pageobject.SplashPageObject
import com.jeff.pizza.pageobject.UserTypePageObject
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@UninstallModules(
    ApplicationModule::class
)
@HiltAndroidTest
class UserNoLoggedInTest : BaseFragmentTest() {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Before
    override fun setUp() {
        super.setUp()
        hiltRule.inject()
    }

    @Test
    fun givenUnselectedUserTypeWhenUserTypeIsClickedThenEnableConfirmButton() {
        sharedPreferences.clear()
        activityRule.launchActivity(null)
        SplashPageObject().assertVisible()
        UserTypePageObject().apply {
            waitForVisible(R.id.titleLogin)
            assertUserTypeNotSelected()
            onUserTypeClick(R.id.singleOptionLogin)
            assertUserTypeSelected(R.id.singleOptionLogin)
        }
    }

}