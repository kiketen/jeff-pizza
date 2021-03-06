package com.jeff.pizza.pageobject

import android.content.Context
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.jeff.pizza.R
import com.jeff.pizza.products.domain.usecase.GetSpecialProductUseCaseImpl.Companion.specialProduct
import com.jeff.pizza.products.presentation.model.ProductDetailsUI
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertDisplayedAtPosition
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaListInteractions.clickListItemChild
import com.schibsted.spain.barista.interaction.BaristaListInteractions.scrollListToPosition
import org.hamcrest.Matchers.not


class ProductDetailsPageObject: PageObject {

    fun assertProductDetailsVisible(productDetails: ProductDetailsUI, context: Context) {
        waitForVisible(R.id.imageProductDetails)
        assertDisplayed(R.id.titleProductDetails, productDetails.name)
        assertDisplayed(R.id.contentProductDetails, productDetails.content)
        productDetails.prices.forEachIndexed { index, price ->
            scrollListToPosition(R.id.pricesProductDetails, index)
            assertDisplayedAtPosition(R.id.pricesProductDetails, index, R.id.sizeProductDetails,
                    context.getString(R.string.product_details_size, price.size)
            )
            assertDisplayedAtPosition(R.id.pricesProductDetails, index, R.id.amountProductDetails,
                    context.getString(R.string.product_details_amount, price.amount, price.currency)
            )
            assertDisplayedAtPosition(R.id.pricesProductDetails, index, R.id.productsNumber, "0")
            assertRecyclerViewItemChild(R.id.pricesProductDetails, R.id.addProductButton, index, isDisplayed())
            assertRecyclerViewItemChild(R.id.pricesProductDetails, R.id.removeProductButton, index, not(isDisplayed()))
        }
    }

    fun assertProductAdded(position: Int) {
        assertCartShoppingWithProducts()
        assertDisplayedAtPosition(R.id.pricesProductDetails, position, R.id.productsNumber, "1")
        assertRecyclerViewItemChild(R.id.pricesProductDetails, R.id.removeProductButton, position, isDisplayed())
    }

    fun assertCartShoppingWithProducts() {
        assertDisplayed(R.id.cartNotification)
        assertDisplayed(R.id.confirmButtonProductDetails)
    }

    fun assertCartShoppingEmpty() {
        assertNotDisplayed(R.id.cartNotification)
        assertNotDisplayed(R.id.confirmButtonProductDetails)
    }

    fun assertAddSpecialProductAlertVisible(context: Context) {
        assertTextVisible(R.string.alert_add_special_product_title)
        assertTextVisible(context.getString(R.string.alert_add_special_product_description,
                specialProduct.name, specialProduct.amount, specialProduct.currency))
        assertTextVisible(R.string.alert_add_special_product_confirm)
        assertTextVisible(R.string.alert_add_special_product_cancel)
    }

    fun clickAddButton(position: Int) {
        clickListItemChild(R.id.pricesProductDetails, position, R.id.addProductButton)
    }

    fun clickRemoveButton(position: Int) {
        clickListItemChild(R.id.pricesProductDetails, position, R.id.removeProductButton)
    }

    fun clickBackButton() {
        clickOn(R.id.backButtonProductDetails)
    }

    fun clickConfirmOrderButton() {
        clickOn(R.id.confirmButtonProductDetails)
    }

    fun clickShoppingCartButton() {
        clickOn(R.id.cartButton)
    }

    fun clickConfirmSpecialProduct() {
        clickOn(R.string.alert_add_special_product_confirm)
    }
}