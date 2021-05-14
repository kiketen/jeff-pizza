package com.jeff.pizza.core.domain.repository.products

import com.jeff.pizza.core.domain.model.products.Product
import com.jeff.pizza.core.domain.model.base.Either
import com.jeff.pizza.core.domain.model.base.Failure


interface ProductsDataSource {
    fun getProducts(): Either<Failure.NoData, List<Product>>
    fun insertProducts(products: List<Product>)
}