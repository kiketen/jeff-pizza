package com.jeff.pizza.pageobject

import android.content.Context
import com.jeff.pizza.R
import com.jeff.pizza.cart.presentation.model.ShoppingCartInfoUI
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertDisplayedAtPosition
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaListInteractions.scrollListToPosition
import com.schibsted.spain.barista.interaction.BaristaScrollInteractions.scrollTo


class ShoppingCartPageObject: PageObject {

    fun assertShoppingCartInfo(shoppingCartInfoUI: ShoppingCartInfoUI, context: Context) {
        waitForVisible(R.id.titleShoppingCart)
        assertDisplayed(R.id.titleShoppingCart, R.string.shopping_cart_title)
        assertDisplayed(R.id.confirmButtonShoppingCart)

        shoppingCartInfoUI.products.forEachIndexed { index, product ->
            scrollListToPosition(R.id.productsShoppingCart, index)
            assertDisplayedAtPosition(R.id.productsShoppingCart, index, R.id.titleProductShoppingCart, product.name)
            product.prices.forEach {
                assertTextVisible(context.getString(R.string.shopping_cart_product_info, it.count, it.text))
                assertTextVisible(context.getString(R.string.product_details_amount, it.amount.toString()))
            }
        }
        scrollTo(R.id.totalAmountShoppingCart)
        assertDisplayed(R.id.totalAmountShoppingCart, context.getString(R.string.shopping_cart_total_amount, shoppingCartInfoUI.totalAmount))
    }

    fun clickConfirmOrderButton() {
        clickOn(R.id.confirmButtonShoppingCart)
    }
}