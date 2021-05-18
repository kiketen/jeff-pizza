package com.jeff.pizza.pageobject

import android.content.Context
import com.jeff.pizza.R
import com.jeff.pizza.products.presentation.model.ProductDetailsUI
import com.schibsted.spain.barista.assertion.BaristaListAssertions
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaListInteractions


class ProductDetailsPageObject: PageObject {

    fun assertProductDetailsVisible(productDetails: ProductDetailsUI, context: Context) {
        waitForVisible(R.id.imageProductDetails)
        assertDisplayed(R.id.titleProductDetails, productDetails.name)
        assertDisplayed(R.id.contentProductDetails, productDetails.content)
        productDetails.prices.forEachIndexed { index, price ->
            BaristaListInteractions.scrollListToPosition(R.id.pricesProductDetails, index)
            BaristaListAssertions.assertDisplayedAtPosition(R.id.pricesProductDetails, index, R.id.sizeProductDetails,
                    context.getString(R.string.product_details_size, price.size)
            )
            BaristaListAssertions.assertDisplayedAtPosition(R.id.pricesProductDetails, index, R.id.amountProductDetails,
                    context.getString(R.string.product_details_amount, price.amount)
            )
        }
    }

    fun clickBackButton() {
        clickOn(R.id.backButtonProductDetails)
    }
}