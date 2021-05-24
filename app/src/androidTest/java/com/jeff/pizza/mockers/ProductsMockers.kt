package com.jeff.pizza.mockers

import com.jeff.pizza.products.presentation.model.ProductUI


private val heura = ProductUI(
        id = 1001,
        name = "¡NUEVO! Pizza Heura",
        imageUrl = "",
        cheaperAmount = 6.5F
)

private val fadrina = ProductUI(
        id = 1000,
        name = "Nueva! Pizza Fadrina",
        imageUrl = "",
        cheaperAmount = 6F
)

private val gourmet = ProductUI(
        id = 1002,
        name = "Pizza Gourmet",
        imageUrl = "",
        cheaperAmount = 6.9F
)

private val borromea = ProductUI(
        id = 1004,
        name = "Pizza Borromea",
        imageUrl = "",
        cheaperAmount = 5F
)

private val espencat = ProductUI(
        id = 1005,
        name = "Pizza Espencat",
        imageUrl = "",
        cheaperAmount = 5.5F
)

private val margarita = ProductUI(
        id = 1003,
        name = "Pizza Margarita",
        imageUrl = "",
        cheaperAmount = 11.5F
)

val singleProducts = listOf(fadrina, heura, gourmet, borromea, espencat, margarita)
val marriedProducts = listOf(fadrina, heura, gourmet, margarita, borromea, espencat)