package com.jeff.pizza.core.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.jeff.pizza.core.domain.model.products.Product


@Entity(primaryKeys = ["id"])
data class ProductApiModel(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("content") val content: String,
        @SerializedName("imageUrl") val imageUrl: String,
        @SerializedName("prices") val prices: List<PriceApiModel>
)

fun List<ProductApiModel>.toDomain() = map { it.toDomain() }

fun List<Product>.toApi() = map { it.toApi() }

fun ProductApiModel.toDomain() =
        Product(
                id = id,
                name = name,
                content = content,
                imageUrl = imageUrl,
                prices = prices.toDomain()
        )

private fun Product.toApi() =
        ProductApiModel(
                id = id,
                name = name,
                content = content,
                imageUrl = imageUrl,
                prices = prices.toApi()
        )