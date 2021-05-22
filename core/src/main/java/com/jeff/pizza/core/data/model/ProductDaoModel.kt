package com.jeff.pizza.core.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeff.pizza.core.domain.model.products.Product


@Entity
data class ProductDaoModel(
        @PrimaryKey
        val id: Long,
        val name: String,
        val content: String,
        val imageUrl: String
)

fun List<Product>.toDao() = map { it.toDao() }

fun Product.toDao() =
        ProductDaoModel(
                id = id,
                name = name,
                content = content,
                imageUrl = imageUrl
        )