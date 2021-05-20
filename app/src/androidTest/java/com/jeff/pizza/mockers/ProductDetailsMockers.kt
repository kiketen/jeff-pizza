package com.jeff.pizza.mockers

import com.jeff.pizza.products.presentation.model.SizeUI
import com.jeff.pizza.products.presentation.model.ProductDetailsUI


val productDetails = ProductDetailsUI(
        id = 1005,
        name = "Pizza Espencat",
        content = "Mozzarella, emmental, berenjena, pimiento rojo asado en aove y aceitunas negras.",
        imageUrl = "https://d1ralsognjng37.cloudfront.net/61c0b3e5-91e0-497a-83a8-6ebcff1aafe3.jpeg",
        sizes = listOf(
                SizeUI("M", 7.5F),
                SizeUI("L", 12.5F),
                SizeUI("S", 5.5F),
                SizeUI("XL", 15.5F),
        )
)