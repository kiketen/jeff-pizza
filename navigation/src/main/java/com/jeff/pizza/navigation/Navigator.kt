package com.jeff.pizza.navigation

import androidx.navigation.NavController


interface Navigator {
    var navController: NavController?
    fun navigateToFlow(navigationFlow: NavigationFlow)
}