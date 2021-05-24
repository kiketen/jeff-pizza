package com.jeff.pizza.core.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeff.pizza.core.domain.model.products.Price

@Entity
class PriceDaoModel(
        @PrimaryKey
        val priceId: String,
        val size: String,
        val amount: Float,
        val currency: String,
        val count: Int,
        val productId: Long,
        val customerSatisfaction: Int
)

fun List<PriceDaoModel>.toDomain() = map { it.toDomain() }

fun List<Price>.toDao(productId: Long) = map { it.toDao(productId) }

fun Price.toDao(productId: Long) =
        PriceDaoModel(
                priceId = productId.toString() + size,
                size = size,
                amount = amount,
                currency = currency,
                count = count,
                productId = productId,
                customerSatisfaction = customerSatisfaction
        )

fun PriceDaoModel.toDomain() =
        Price(
                size = size,
                amount = amount,
                currency = currency,
                customerSatisfaction = customerSatisfaction,
                count = count
        )