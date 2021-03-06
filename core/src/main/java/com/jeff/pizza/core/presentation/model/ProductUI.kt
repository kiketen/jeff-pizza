package com.jeff.pizza.core.presentation.model

import com.jeff.pizza.core.domain.model.products.Price
import com.jeff.pizza.core.domain.model.products.Product


data class ProductUI(
        val id: Long,
        val name: String,
        val imageUrl: String,
        val cheaperAmount: Float?,
        val currency: String
)

fun List<Product>.toUI() = map { it.toUI() }

private fun Product.toUI() =
        ProductUI(
                id = id,
                name = name,
                imageUrl = imageUrl,
                cheaperAmount = prices.getCheaperAmount(),
                currency = prices.first().currency
        )

private fun List<Price>.getCheaperAmount() = minByOrNull { it.amount }?.amount