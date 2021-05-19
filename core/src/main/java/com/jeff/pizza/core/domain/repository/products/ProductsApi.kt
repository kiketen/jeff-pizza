package com.jeff.pizza.core.domain.repository.products

import com.jeff.pizza.core.domain.model.base.Either
import com.jeff.pizza.core.domain.model.base.Failure
import com.jeff.pizza.core.domain.model.products.Product
import com.jeff.pizza.core.domain.model.user.UserType


interface ProductsApi {
    fun getProducts(userType: UserType): Either<Failure, List<Product>>
}