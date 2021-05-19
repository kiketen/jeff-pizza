package com.jeff.pizza.core.domain.resource.products

import com.jeff.pizza.core.domain.model.base.Either
import com.jeff.pizza.core.domain.model.base.Failure
import com.jeff.pizza.core.domain.model.products.Product
import com.jeff.pizza.core.domain.model.user.UserType
import com.jeff.pizza.core.domain.repository.products.ProductsApi
import com.jeff.pizza.core.domain.repository.products.ProductsDataSource
import javax.inject.Inject


class ProductsResourceImpl
@Inject constructor(
        private val dataSourceRepository: ProductsDataSource,
        private val apiRepository: ProductsApi
): ProductsResource {

    override fun getProducts(refresh: Boolean, userType: UserType): Either<Failure, List<Product>> {
        return if (refresh) {
            getProductsFromApiAndUpdateDataSource(userType)
        } else {
            val products = dataSourceRepository.getProducts(userType)
            if (products.isLeft) {
                getProductsFromApiAndUpdateDataSource(userType)
            } else {
                products
            }
        }
    }

    override fun getProduct(productId: Long, userType: UserType): Product {
        return dataSourceRepository.getProduct(productId, userType)
    }

    private fun getProductsFromApiAndUpdateDataSource(userType: UserType) = apiRepository.getProducts(userType).apply {
        either(onSuccess = { dataSourceRepository.insertProducts(it) })
    }

}