package com.jeff.pizza.products.presentation.ui.details

import com.jeff.pizza.products.presentation.model.ProductDetailsUI
import com.jeff.pizza.products.presentation.model.ProductUI


sealed class ProductDetailsUIState {
    class ShowingContent(val details: ProductDetailsUI): ProductDetailsUIState()
}
