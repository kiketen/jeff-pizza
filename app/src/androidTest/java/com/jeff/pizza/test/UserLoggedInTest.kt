package com.jeff.pizza.test

import android.content.SharedPreferences
import com.jeff.pizza.R
import com.jeff.pizza.base.BaseFragmentTest
import com.jeff.pizza.core.data.repository.user.UserDataSourceRepository
import com.jeff.pizza.core.domain.model.user.UserType
import com.jeff.pizza.di.ApplicationModule
import com.jeff.pizza.mockers.products
import com.jeff.pizza.pageobject.ProductsPageObject
import com.jeff.pizza.pageobject.SplashPageObject
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
class UserLoggedInTest: BaseFragmentTest() {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var userDataSource: UserDataSourceRepository

    @Before
    override fun setUp() {
        super.setUp()
        hiltRule.inject()
    }

    @Test
    fun givenLoggedInUserTypeWhenStartsAppThenShowProducts() {
        userDataSource.setUserType(UserType.SINGLE)
        mockServer.dispatcher = object: Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                        .setResponseCode(200)
                        .setBody(readStringFromFile("products.json"))
            }
        }
        activityRule.launchActivity(null)
        SplashPageObject().assertVisible()
        ProductsPageObject().apply {
            waitForVisible(R.id.listProducts)
            assertProductsVisible(products, activityRule.activity)
        }
    }

}