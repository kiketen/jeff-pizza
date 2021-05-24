package com.jeff.pizza.products.presentation.model

import com.jeff.pizza.core.presentation.model.ProductUI


sealed class ProductsUIState {
    object Loading: ProductsUIState()
    class ShowingContent(val products: List<ProductUI>): ProductsUIState()
}
