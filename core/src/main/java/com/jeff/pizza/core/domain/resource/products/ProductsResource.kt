package com.jeff.pizza.core.domain.resource.products

import com.jeff.pizza.core.domain.model.products.Product
import com.jeff.pizza.core.domain.model.base.Either
import com.jeff.pizza.core.domain.model.base.Failure


interface ProductsResource {
    fun getProducts(refresh: Boolean): Either<Failure, List<Product>>
}