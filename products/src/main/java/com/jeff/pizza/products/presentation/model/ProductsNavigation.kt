package com.jeff.pizza.products.presentation.model


sealed class ProductsNavigation {
    class ProductDetails(val productId: Long) : ProductsNavigation()
}
