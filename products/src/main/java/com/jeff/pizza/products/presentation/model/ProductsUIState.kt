package com.jeff.pizza.products.presentation.model


sealed class ProductsUIState {
    object Loading: ProductsUIState()
    class ShowingContent(val products: List<ProductUI>): ProductsUIState()
}
