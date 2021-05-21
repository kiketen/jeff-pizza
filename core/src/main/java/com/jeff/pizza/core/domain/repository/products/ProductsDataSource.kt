package com.jeff.pizza.core.domain.repository.products

import com.jeff.pizza.core.domain.model.base.Either
import com.jeff.pizza.core.domain.model.base.Failure
import com.jeff.pizza.core.domain.model.products.Product
import com.jeff.pizza.core.domain.model.user.UserType


interface ProductsDataSource {
    fun getProducts(userType: UserType = UserType.UNKNOWN): Either<Failure.NoData, List<Product>>
    fun getProduct(productId: Long, userType: UserType = UserType.UNKNOWN): Product
    fun insertProducts(products: List<Product>)
    fun updateProduct(productIncreased: Product)
}