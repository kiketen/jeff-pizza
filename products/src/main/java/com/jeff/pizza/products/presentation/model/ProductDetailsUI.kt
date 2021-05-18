package com.jeff.pizza.products.presentation.model


data class ProductDetailsUI(
        val id: Long,
        val name: String,
        val content: String,
        val imageUrl: String,
        val prices: List<PriceUI>
)