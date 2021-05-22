package com.jeff.pizza.core.data.model

import com.google.gson.annotations.SerializedName
import com.jeff.pizza.core.domain.model.products.Product
import com.jeff.pizza.core.domain.model.user.UserType


data class ProductApiModel(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("content") val content: String,
        @SerializedName("imageUrl") val imageUrl: String,
        @SerializedName("prices") val prices: List<PriceApiModel>
)

fun List<ProductApiModel>.toDomain(userType: UserType) = map { it.toDomain(userType) }
        .sortedByDescending { it.prices.maxOf { price -> price.customerSatisfaction } }

fun ProductApiModel.toDomain(userType: UserType) =
        Product(
                id = id,
                name = name,
                content = content,
                imageUrl = imageUrl,
                prices = prices.toDomain(userType)
        )