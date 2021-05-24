package com.jeff.pizza.pageobject

import android.content.Context
import com.jeff.pizza.R
import com.jeff.pizza.core.presentation.model.ProductUI
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertDisplayedAtPosition
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.interaction.BaristaListInteractions.clickListItem
import com.schibsted.spain.barista.interaction.BaristaListInteractions.scrollListToPosition


class ProductsPageObject: PageObject {

    fun assertProductsVisible(products: List<ProductUI>, context: Context) {
        products.forEachIndexed { index, product ->
            scrollListToPosition(R.id.listProducts, index)
            assertDisplayedAtPosition(R.id.listProducts, index, R.id.titleProduct, product.name)
            assertDisplayedAtPosition(R.id.listProducts, index, R.id.priceProduct,
                    context.resources.getString(R.string.product_price_since,
                            product.cheaperAmount.toString(), product.currency))
        }
    }

    fun assertCartShoppingWithProducts() {
        assertDisplayed(R.id.cartNotification)
    }

    fun assertCartShoppingEmpty() {
        assertNotDisplayed(R.id.cartNotification)
    }

    fun clickProduct(itemPosition: Int) {
        scrollListToPosition(R.id.listProducts, itemPosition)
        clickListItem(R.id.listProducts, itemPosition)
    }
}