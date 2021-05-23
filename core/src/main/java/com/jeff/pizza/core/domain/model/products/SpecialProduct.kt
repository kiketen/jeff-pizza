package com.jeff.pizza.core.domain.model.products


data class SpecialProduct(
        val name: String,
        val amount: Float,
        val currency: String,
        val count: Int
)