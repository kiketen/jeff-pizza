package com.jeff.pizza.test

import android.content.SharedPreferences
import com.jeff.pizza.R
import com.jeff.pizza.base.BaseFragmentTest
import com.jeff.pizza.di.ApplicationModule
import com.jeff.pizza.mockers.singleProducts
import com.jeff.pizza.pageobject.ProductsPageObject
import com.jeff.pizza.pageobject.SplashPageObject
import com.jeff.pizza.pageobject.UserTypePageObject
import com.jeff.pizza.utils.readStringFromFile
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@UninstallModules(
        ApplicationModule::class
)
@HiltAndroidTest
class UserNoLoggedInTest: BaseFragmentTest() {

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
    fun givenUserNotLoggedInWhenLoginSuccessThenDoLogout() {
        sharedPreferences.clear()
        mockGetProductsSuccess()
        activityRule.launchActivity(null)

        SplashPageObject().assertVisible()

        val userTypeObject = UserTypePageObject().apply {
            assertUserTypeNotSelected()
            onUserTypeClick(R.id.singleOptionLogin)
            assertUserTypeSelected(R.id.singleOptionLogin)
            onConfirmButtonClick()
        }
        ProductsPageObject().apply {
            waitForVisible(R.id.listProducts)
            assertProductsVisible(singleProducts, activityRule.activity)
            clickLogoutButton()
        }
        userTypeObject.assertUserTypeNotSelected()
    }

    @Test
    fun givenUserNotLoggedInWhenLoginErrorThenShowError() {
        sharedPreferences.clear()
        mockGetProductsError()
        activityRule.launchActivity(null)

        UserTypePageObject().apply {
            waitForVisible(R.id.titleLogin)
            onUserTypeClick(R.id.singleOptionLogin)
            onConfirmButtonClick()
            assertLoginError()
        }
    }

    private fun mockGetProductsError() {
        mockServer.dispatcher = object: Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                        .setResponseCode(400)
            }
        }
    }

    private fun mockGetProductsSuccess() {
        mockServer.dispatcher = object: Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                        .setBody(readStringFromFile("products.json"))
            }
        }
    }

}