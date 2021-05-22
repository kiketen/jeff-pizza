package com.jeff.pizza.cart.presentation.model

import com.jeff.pizza.core.domain.model.products.Product


data class ShoppingCartProductUI(
        val name: String,
        val prices: List<ShoppingCartPriceUI>
)

fun List<Product>.toUI() = map { it.toUI() }

private fun Product.toUI() =
        ShoppingCartProductUI(
                name = name,
                prices = prices.toUI()
        )