package com.jeff.pizza.products.presentation.model

import androidx.annotation.StringRes


sealed class ProductsError {
    class ErrorIndefinite(@StringRes val textId: Int): ProductsError()
    class ErrorShort(@StringRes val textId: Int): ProductsError()
}
