package com.jeff.pizza.cart.domain.model

import com.jeff.pizza.core.domain.model.products.Product


data class ShoppingCartInfo(
        val products: List<Product>,
        val specialProduct: SpecialProduct?,
        val totalAmount: Float
)
