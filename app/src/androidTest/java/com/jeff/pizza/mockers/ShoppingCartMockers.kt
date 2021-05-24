package com.jeff.pizza.mockers

import com.jeff.pizza.cart.presentation.model.ShoppingCartInfoUI
import com.jeff.pizza.cart.presentation.model.ShoppingCartPriceUI
import com.jeff.pizza.cart.presentation.model.ShoppingCartProductUI
import com.jeff.pizza.cart.presentation.model.toUI
import com.jeff.pizza.products.domain.usecase.GetSpecialProductUseCaseImpl.Companion.specialProduct


val shoppingCartInfo = ShoppingCartInfoUI(
        products = listOf(
                ShoppingCartProductUI(
                        name = pizzaEspencatDetails.name,
                        prices = listOf(
                                ShoppingCartPriceUI(
                                        count = 1,
                                        text = "L",
                                        amount = 12.5F,
                                        currency = "€")
                        )
                ),
                ShoppingCartProductUI(
                        name = pizzaMargaritaDetails.name,
                        prices = listOf(
                                ShoppingCartPriceUI(
                                        count = 1,
                                        text = "XL",
                                        amount = 14.5F,
                                        currency = "€"
                                )
                        )
                )
        ),
        specialProduct = null,
        totalAmount = 27.0F,
        currency = "€"
)

val shoppingCartMarriedInfo = ShoppingCartInfoUI(
        products = listOf(
                ShoppingCartProductUI(
                        name = pizzaEspencatMarriedDetails.name,
                        prices = listOf(
                                ShoppingCartPriceUI(
                                        count = 1,
                                        text = "XL",
                                        amount = 15.5F,
                                        currency = "€"
                                )
                        )
                )
        ),
        specialProduct = specialProduct.toUI(),
        totalAmount = 19F,
        currency = "€"
)
