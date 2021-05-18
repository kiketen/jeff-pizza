package com.jeff.pizza.core.domain.repository.products

import com.jeff.pizza.core.domain.model.base.Either
import com.jeff.pizza.core.domain.model.base.Failure
import com.jeff.pizza.core.domain.model.products.Product


interface ProductsDataSource {
    fun getProducts(): Either<Failure.NoData, List<Product>>
    fun getProduct(productId: Long): Product
    fun insertProducts(products: List<Product>)
}