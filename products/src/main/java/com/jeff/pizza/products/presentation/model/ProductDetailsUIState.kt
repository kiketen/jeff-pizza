package com.jeff.pizza.products.presentation.model


sealed class ProductDetailsUIState {
    class ShowingContent(val details: ProductDetailsUI): ProductDetailsUIState()
    object Back: ProductDetailsUIState()
}
