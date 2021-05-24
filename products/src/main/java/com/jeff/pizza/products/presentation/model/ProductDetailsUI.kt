package com.jeff.pizza.products.presentation.model

import com.jeff.pizza.core.domain.model.products.Product
import com.jeff.pizza.core.domain.model.products.Price


data class ProductDetailsUI(
        val id: Long,
        val name: String,
        val content: String,
        val imageUrl: String,
        val prices: List<PriceUI>
)

fun Product.toDetailsUI() =
        ProductDetailsUI(
                id = id,
                name = name,
                content = content,
                imageUrl = imageUrl,
                prices = prices.toUI()
        )

private fun List<Price>.toUI() = map { it.toUI() }

private fun Price.toUI() =
        PriceUI(
                size = size,
                amount = amount,
                count = count,
                currency = currency
        )