package com.jeff.pizza.navigation


sealed class NavigationFlow {
    object Login: NavigationFlow()
    object Products: NavigationFlow()
}
