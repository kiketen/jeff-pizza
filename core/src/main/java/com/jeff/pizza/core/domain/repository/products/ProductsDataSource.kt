package com.jeff.pizza.core.domain.repository.products

import com.jeff.pizza.core.domain.model.base.Either
import com.jeff.pizza.core.domain.model.base.Failure
import com.jeff.pizza.core.domain.model.products.Product
import com.jeff.pizza.core.domain.model.user.UserType


interface ProductsDataSource {
    fun getProducts(userType: UserType): Either<Failure.NoData, List<Product>>
    fun getProduct(productId: Long, userType: UserType): Product
    fun insertProducts(products: List<Product>)
    fun updateProduct(productIncreased: Product)
}