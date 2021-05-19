package com.jeff.pizza.core.data.repository.products

import com.jeff.pizza.core.data.extension.make
import com.jeff.pizza.core.data.model.toDomain
import com.jeff.pizza.core.domain.model.base.Either
import com.jeff.pizza.core.domain.model.base.Failure
import com.jeff.pizza.core.domain.model.products.Product
import com.jeff.pizza.core.domain.model.user.UserType
import com.jeff.pizza.core.domain.repository.products.ProductsApi
import javax.inject.Inject


class ProductsApiImpl @Inject constructor(
    private val productsApiDefinition: ProductsApiDefinition
) : ProductsApi {

    override fun getProducts(userType: UserType): Either<Failure, List<Product>> {
        return productsApiDefinition.getProducts().make { it.toDomain(userType) }
    }
}