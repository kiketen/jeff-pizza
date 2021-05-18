package com.jeff.pizza.products.presentation.ui.list

import com.jeff.pizza.products.presentation.model.ProductUI


sealed class ProductsUIState {
    object Loading: ProductsUIState()
    class ShowingContent(val products: List<ProductUI>): ProductsUIState()
    object Error: ProductsUIState()
}
