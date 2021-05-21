package com.jeff.pizza.core.domain.resource.products

import com.jeff.pizza.core.domain.model.base.Either
import com.jeff.pizza.core.domain.model.base.Failure
import com.jeff.pizza.core.domain.model.products.Product
import com.jeff.pizza.core.domain.model.user.UserType


interface ProductsResource {
    fun getProducts(refresh: Boolean, userType: UserType = UserType.UNKNOWN): Either<Failure, List<Product>>
    fun getProduct(productId: Long, userType: UserType = UserType.UNKNOWN): Product
    fun addProduct(productId: Long, size: String): Product
    fun removeProduct(productId: Long, size: String): Product
}