package com.jeff.pizza.cart.presentation.model


data class ShoppingCartInfoUI(
        val products: List<ShoppingCartProductUI>,
        val specialProduct: ShoppingCartPriceUI,
        val total: Float
)