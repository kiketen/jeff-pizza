package com.jeff.pizza.core.data.repository.products

import com.jeff.pizza.core.data.model.toApi
import com.jeff.pizza.core.data.model.toDomain
import com.jeff.pizza.core.domain.model.base.Either
import com.jeff.pizza.core.domain.model.base.Failure
import com.jeff.pizza.core.domain.model.products.Product
import com.jeff.pizza.core.domain.model.user.UserType
import com.jeff.pizza.core.domain.repository.products.ProductsDataSource
import javax.inject.Inject


class ProductsDataSourceImpl @Inject constructor(
        private val productsDAO: ProductsDAO
): ProductsDataSource {

    override fun getProducts(userType: UserType): Either<Failure.NoData, List<Product>> {
        val products = productsDAO.getProducts().toDomain(userType)
        return if (products.isEmpty()) {
            Either.Left(Failure.NoData)
        } else {
            Either.Right(products)
        }
    }

    override fun getProduct(productId: Long, userType: UserType): Product {
        return productsDAO.getProduct(productId).toDomain(userType)
    }

    override fun insertProducts(products: List<Product>) {
        productsDAO.insertProducts(products.toApi())
    }

    override fun updateProduct(productIncreased: Product) {
        productsDAO.updateProduct(productIncreased.toApi())
    }
}