package com.jeff.pizza.products.presentation.model

import com.jeff.pizza.core.domain.model.products.Product
import com.jeff.pizza.core.domain.model.products.Size


data class ProductDetailsUI(
        val id: Long,
        val name: String,
        val content: String,
        val imageUrl: String,
        val sizes: List<SizeUI>
)

fun Product.toDetailsUI() =
        ProductDetailsUI(
                id = id,
                name = name,
                content = content,
                imageUrl = imageUrl,
                sizes = sizes.toUI()
        )

private fun List<Size>.toUI() = map { it.toUI() }

private fun Size.toUI() =
        SizeUI(
                size = size,
                amount = amount,
                count = count
        )