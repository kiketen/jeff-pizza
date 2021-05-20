package com.jeff.pizza.products.presentation.model

import com.jeff.pizza.core.domain.model.products.Size
import com.jeff.pizza.core.domain.model.products.Product


data class ProductUI(
        val id: Long,
        val name: String,
        val imageUrl: String,
        val cheaperAmount: Float?
)

fun List<Product>.toUI() = map { it.toUI() }

private fun Product.toUI() =
        ProductUI(
                id = id,
                name = name,
                imageUrl = imageUrl,
                cheaperAmount = sizes.getCheaperAmount()
        )

private fun List<Size>.getCheaperAmount() = minByOrNull { it.amount }?.amount