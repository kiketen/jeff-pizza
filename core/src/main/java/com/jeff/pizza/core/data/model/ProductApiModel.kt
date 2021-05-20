package com.jeff.pizza.core.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.jeff.pizza.core.domain.model.products.Product
import com.jeff.pizza.core.domain.model.user.UserType


@Entity(primaryKeys = ["id"])
data class ProductApiModel(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("content") val content: String,
        @SerializedName("imageUrl") val imageUrl: String,
        @SerializedName("prices") val prices: List<PriceApiModel>
)

fun List<ProductApiModel>.toDomain(userType: UserType) = map { it.toDomain(userType) }
        .sortedByDescending { it.sizes.maxOf { price -> price.customerSatisfaction } }

fun List<Product>.toApi() = map { it.toApi() }

fun ProductApiModel.toDomain(userType: UserType) =
        Product(
                id = id,
                name = name,
                content = content,
                imageUrl = imageUrl,
                sizes = prices.toDomain(userType)
        )

private fun Product.toApi() =
        ProductApiModel(
                id = id,
                name = name,
                content = content,
                imageUrl = imageUrl,
                prices = sizes.toApi()
        )