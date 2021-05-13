package com.jeff.pizza.test.login

import com.jeff.pizza.base.BaseFragmentTest
import com.jeff.pizza.di.ApplicationModule
import com.jeff.pizza.login.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Rule
import org.junit.Test

@UninstallModules(
        ApplicationModule::class
)
@HiltAndroidTest
class UserTypeFragmentTest: BaseFragmentTest() {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun givenUnselectedUserTypeWhenUserTypeIsClickedThenEnableConfirmButton() {
        activityRule.launchActivity(null)
        UserTypePageObject().apply {
            assertUserTypeNotSelected()
            onUserTypeClick(R.id.singleOptionLogin)
            assertUserTypeSelected(R.id.singleOptionLogin)
        }
    }


}