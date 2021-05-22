package com.jeff.pizza.cart.presentation.model


data class ShoppingCartProductUI(
        val name: String,
        val prices: List<ShoppingCartPriceUI>
)