package com.jeff.pizza.products.presentation.model


data class PriceUI(
        val size: String,
        val amount: Float,
        val currency: String,
        val count: Int
)