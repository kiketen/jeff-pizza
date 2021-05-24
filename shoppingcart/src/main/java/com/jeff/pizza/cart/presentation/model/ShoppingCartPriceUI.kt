package com.jeff.pizza.cart.presentation.model

import com.jeff.pizza.core.domain.model.products.Price
import com.jeff.pizza.core.domain.model.products.SpecialProduct


data class ShoppingCartPriceUI(
        val count: Int,
        val text: String,
        val amount: Float,
        val currency: String
)

fun SpecialProduct.toUI() =
        ShoppingCartPriceUI(
                count = count,
                text = name,
                amount = amount,
                currency = currency
        )

fun List<Price>.toUI() = map { it.toUI() }

private fun Price.toUI() =
        ShoppingCartPriceUI(
                count = count,
                text = size,
                amount = amount,
                currency = currency
        )
