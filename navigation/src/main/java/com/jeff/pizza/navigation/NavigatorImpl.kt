package com.jeff.pizza.navigation

import androidx.navigation.NavController
import com.jeff.pizza.navigation.MainNavGraphDirections
import javax.inject.Inject


class NavigatorImpl @Inject constructor(): Navigator {

    override var navController: NavController? = null

    override fun navigateToFlow(navigationFlow: NavigationFlow) {
        when (navigationFlow) {
            NavigationFlow.Login -> navController?.navigate(MainNavGraphDirections.actionGlobalLoginFlow())
            NavigationFlow.Products -> navController?.navigate(MainNavGraphDirections.actionGlobalProductsFlow())
            NavigationFlow.ShoppingCart -> navController?.navigate(MainNavGraphDirections.actionGlobalShoppingCartFlow())
        }
    }
}