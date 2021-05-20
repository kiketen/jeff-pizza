package com.jeff.pizza.core.data.model

import com.google.gson.annotations.SerializedName
import com.jeff.pizza.core.domain.model.products.Size
import com.jeff.pizza.core.domain.model.user.UserType


class PriceApiModel(
        @SerializedName("size") val size: String,
        @SerializedName("price") val amount: Float,
        @SerializedName("count") val count: Int
)

fun List<PriceApiModel>.toDomain(userType: UserType) = map { it.toDomain(userType) }.sortedByDescending { it.customerSatisfaction }

fun List<Size>.toApi() = map { it.toApi() }

private fun PriceApiModel.toDomain(userType: UserType) =
        Size(
                size = size,
                amount = amount,
                customerSatisfaction = getCustomerSatisfaction(userType, ProductSize.fromStringName(size)),
                count = count
        )

private fun getCustomerSatisfaction(userType: UserType, size: ProductSize) =
        when (userType) {
            UserType.MARRIED -> getMarriedSatisfaction(size)
            UserType.SINGLE -> getSingleSatisfaction(size)
            UserType.UNKNOWN -> 0
        }

private fun getMarriedSatisfaction(size: ProductSize) =
        when (size) {
            ProductSize.S -> 3
            ProductSize.M -> 5
            ProductSize.L -> 8
            ProductSize.XL -> 9
            ProductSize.UNKNOWN -> 0
        }

private fun getSingleSatisfaction(size: ProductSize) =
        when (size) {
            ProductSize.S -> 6
            ProductSize.M -> 9
            ProductSize.L -> 8
            ProductSize.XL -> 5
            ProductSize.UNKNOWN -> 0
        }


private fun Size.toApi() =
        PriceApiModel(
                size = size,
                amount = amount,
                count = count
        )