package com.jeff.pizza.core.data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.jeff.pizza.core.domain.model.products.Product


data class ProductAndPricesDaoModel(
        @Embedded val product: ProductDaoModel,
        @Relation(
                parentColumn = "id",
                entityColumn = "productId"
        ) val prices: List<PriceDaoModel>
)

fun List<ProductAndPricesDaoModel>.toDomain() = map { it.toDomain() }
        .sortedByDescending { it.prices.maxOf { price -> price.customerSatisfaction } }

fun ProductAndPricesDaoModel.toDomain() =
        Product(
                id = product.id,
                name = product.name,
                content = product.content,
                imageUrl = product.imageUrl,
                prices = prices.toDomain()
        )