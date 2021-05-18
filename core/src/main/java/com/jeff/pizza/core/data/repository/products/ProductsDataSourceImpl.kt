package com.jeff.pizza.core.data.repository.products

import com.jeff.pizza.core.data.model.toApi
import com.jeff.pizza.core.data.model.toDomain
import com.jeff.pizza.core.domain.model.base.Either
import com.jeff.pizza.core.domain.model.base.Failure
import com.jeff.pizza.core.domain.model.products.Product
import com.jeff.pizza.core.domain.repository.products.ProductsDataSource
import javax.inject.Inject


class ProductsDataSourceImpl @Inject constructor(
        private val productsDAO: ProductsDAO
): ProductsDataSource {

    override fun getProducts(): Either<Failure.NoData, List<Product>> {
        val products = productsDAO.getProducts().toDomain()
        return if (products.isEmpty()) {
            Either.Left(Failure.NoData)
        } else {
            Either.Right(products)
        }
    }

    override fun getProduct(productId: Long): Product {
        return productsDAO.getProduct(productId).toDomain()
    }

    override fun insertProducts(products: List<Product>) {
        productsDAO.insertProducts(products.toApi())
    }
}