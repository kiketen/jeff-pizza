package com.jeff.pizza.cart.model


data class ShoppingCartProductUI(
        val name: String,
        val prices: List<ShoppingCartPriceUI>
)