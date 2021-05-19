package com.jeff.pizza.core.data.model

import com.google.gson.annotations.SerializedName
import com.jeff.pizza.core.domain.model.products.Price
import com.jeff.pizza.core.domain.model.user.UserType


class PriceApiModel(
        @SerializedName("size") val size: String,
        @SerializedName("price") val amount: Float
)

fun List<PriceApiModel>.toDomain(userType: UserType) = map { it.toDomain(userType) }.sortedByDescending { it.customerSatisfaction }

fun List<Price>.toApi() = map { it.toApi() }

private fun PriceApiModel.toDomain(userType: UserType) =
        Price(
                size = size,
                amount = amount,
                customerSatisfaction = getCustomerSatisfaction(userType, ProductSize.fromStringName(size))
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


private fun Price.toApi() =
        PriceApiModel(
                size = size,
                amount = amount
        )