package com.jeff.pizza.core.domain.model.products


data class Size(
        val size: String,
        val amount: Float,
        val customerSatisfaction: Int,
        val count: Int
)