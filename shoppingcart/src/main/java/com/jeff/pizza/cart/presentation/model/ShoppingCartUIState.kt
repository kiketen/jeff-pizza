package com.jeff.pizza.cart.presentation.model


sealed class ShoppingCartUIState {
    class ShowingContent(val shoppingCartInfoUI: ShoppingCartInfoUI): ShoppingCartUIState()
}
