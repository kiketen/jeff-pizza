package com.jeff.pizza.cart.presentation.model

import com.jeff.pizza.core.domain.model.shoppingcart.ShoppingCartInfo


data class ShoppingCartInfoUI(
        val products: List<ShoppingCartProductUI>,
        val specialProduct: ShoppingCartPriceUI?,
        val totalAmount: Float
)

fun ShoppingCartInfo.toUI() =
        ShoppingCartInfoUI(
                products = products.toUI(),
                specialProduct = specialProduct?.toUI(),
                totalAmount = totalAmount
        )