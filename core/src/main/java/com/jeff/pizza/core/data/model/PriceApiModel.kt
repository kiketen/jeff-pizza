package com.jeff.pizza.core.data.model

import com.google.gson.annotations.SerializedName
import com.jeff.pizza.core.domain.model.products.Price


class PriceApiModel(
        @SerializedName("size") val size: String,
        @SerializedName("price") val amount: Float
)

fun List<PriceApiModel>.toDomain() = map { it.toDomain() }

fun List<Price>.toApi() = map { it.toApi() }

private fun PriceApiModel.toDomain() =
        Price(
                size = size,
                amount = amount
        )

private fun Price.toApi() =
        PriceApiModel(
                size = size,
                amount = amount
        )