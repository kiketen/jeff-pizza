package com.jeff.pizza.core.domain.model.products


data class Price(
        val size: String,
        val amount: Float,
        val currency: String,
        val customerSatisfaction: Int,
        val count: Int
)