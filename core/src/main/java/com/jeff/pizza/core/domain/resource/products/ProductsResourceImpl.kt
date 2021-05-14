package com.jeff.pizza.core.domain.resource.products

import com.jeff.pizza.core.domain.model.products.Product
import com.jeff.pizza.core.domain.repository.products.ProductsApi
import com.jeff.pizza.core.domain.repository.products.ProductsDataSource
import com.jeff.pizza.core.domain.model.base.Either
import com.jeff.pizza.core.domain.model.base.Failure
import javax.inject.Inject


class ProductsResourceImpl
@Inject constructor(
        private val dataSourceRepository: ProductsDataSource,
        private val apiRepository: ProductsApi
): ProductsResource {

    override fun getProducts(refresh: Boolean): Either<Failure, List<Product>> {
        return if (refresh) {
            getProductsFromApiAndUpdateDataSource()
        } else {
            val products = dataSourceRepository.getProducts()
            if (products.isLeft) {
                getProductsFromApiAndUpdateDataSource()
            } else {
                products
            }
        }
    }

    private fun getProductsFromApiAndUpdateDataSource() = apiRepository.getProducts().apply {
        either(onSuccess = { dataSourceRepository.insertProducts(it) })
    }

}