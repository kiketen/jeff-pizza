package com.jeff.pizza.products.presentation.ui.list


sealed class ProductsNavigation {
    class ProductDetails(val productId: Long) : ProductsNavigation()
}
