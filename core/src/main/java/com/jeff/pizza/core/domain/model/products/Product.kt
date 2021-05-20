package com.jeff.pizza.core.domain.model.products


data class Product(
        val id: Long,
        val name: String,
        val content: String,
        val imageUrl: String,
        val sizes: List<Size>
)