package com.jeff.pizza.core.domain.model.shoppingcart

import com.jeff.pizza.core.domain.model.products.Product
import com.jeff.pizza.core.domain.model.products.SpecialProduct


data class ShoppingCartInfo(
        val products: List<Product>,
        val specialProduct: SpecialProduct?,
        val totalAmount: Float = 0F
)
