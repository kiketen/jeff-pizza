package com.jeff.pizza.core.domain.repository.products

import com.jeff.pizza.core.domain.model.products.Product
import com.jeff.pizza.core.domain.model.base.Either
import com.jeff.pizza.core.domain.model.base.Failure


interface ProductsApi {
    fun getProducts(): Either<Failure, List<Product>>
}