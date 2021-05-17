package com.jeff.pizza.pageobject

import android.content.Context
import androidx.annotation.IdRes
import com.jeff.pizza.R
import com.jeff.pizza.products.presentation.model.ProductUI
import com.jeff.pizza.utils.ConditionWatcher
import com.jeff.pizza.utils.WaitForVisibleViewInstruction
import com.schibsted.spain.barista.assertion.BaristaListAssertions
import com.schibsted.spain.barista.interaction.BaristaListInteractions


class ProductsPageObject {

    fun waitForVisible(@IdRes id: Int) {
        ConditionWatcher.waitForCondition(
                WaitForVisibleViewInstruction(id)
        )
    }

    fun assertProductsVisible(products: List<ProductUI>, context: Context) {
        products.forEachIndexed { index, product ->
            BaristaListInteractions.scrollListToPosition(R.id.listProducts, index)
            BaristaListAssertions.assertDisplayedAtPosition(R.id.listProducts, index, R.id.titleProduct, product.name)
            BaristaListAssertions.assertDisplayedAtPosition(R.id.listProducts, index, R.id.priceProduct,
                    context.resources.getString(R.string.product_price_since, product.cheaperAmount.toString()))
        }
    }
}