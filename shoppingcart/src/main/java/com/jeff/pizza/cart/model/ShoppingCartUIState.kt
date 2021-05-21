package com.jeff.pizza.cart.model


sealed class ShoppingCartUIState {
    class ShowingContent(): ShoppingCartUIState()
}
