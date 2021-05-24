package com.jeff.pizza.mockers

import com.jeff.pizza.products.presentation.model.PriceUI
import com.jeff.pizza.products.presentation.model.ProductDetailsUI


val pizzaEspencatDetails = ProductDetailsUI(
        id = 1005,
        name = "Pizza Espencat",
        content = "Mozzarella, emmental, berenjena, pimiento rojo asado en aove y aceitunas negras.",
        imageUrl = "https://d1ralsognjng37.cloudfront.net/61c0b3e5-91e0-497a-83a8-6ebcff1aafe3.jpeg",
        prices = listOf(
                PriceUI("M", 7.5F, "€", 0),
                PriceUI("L", 12.5F, "€", 0),
                PriceUI("S", 5.5F, "€", 0),
                PriceUI("XL", 15.5F, "€", 0),
        )
)

val pizzaMargaritaDetails = ProductDetailsUI(
        id = 1003,
        name = "Pizza Margarita",
        content = "Mozzarella, emmental y aceitunas negras.",
        imageUrl = "https://d1ralsognjng37.cloudfront.net/9fb33a40-d775-4d27-b0cb-7a57fc98d3ad.jpeg",
        prices = listOf(
                PriceUI("L", 11.5F, "€", 0),
                PriceUI("XL", 14.5F, "€", 0),
        )
)

val pizzaEspencatMarriedDetails = pizzaEspencatDetails.copy(prices = listOf(
        PriceUI("XL", 15.5F, "€", 0),
        PriceUI("L", 12.5F, "€", 0),
        PriceUI("M", 7.5F, "€", 0),
        PriceUI("S", 5.5F, "€", 0),
))