package com.jeff.pizza.core.domain.resource.products

import com.jeff.pizza.core.domain.model.base.Either
import com.jeff.pizza.core.domain.model.base.Failure
import com.jeff.pizza.core.domain.model.products.Product
import com.jeff.pizza.core.domain.model.products.SpecialProduct
import com.jeff.pizza.core.domain.model.shoppingcart.ShoppingCartInfo
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
            val products = dataSourceRepository.getProducts()
            if (products.isLeft) {
                getProductsFromApiAndUpdateDataSource(userType)
            } else {
                products
            }
        }
    }

    override fun getProduct(productId: Long): Product {
        return dataSourceRepository.getProduct(productId)
    }

    override fun addProductPrice(productId: Long, size: String): Product {
        val price = dataSourceRepository.getProductPrice(productId, size)
        dataSourceRepository.updateProductPrice(price.copy(count = price.count + 1), productId)
        return dataSourceRepository.getProduct(productId)
    }

    override fun removeProductPrice(productId: Long, size: String): Product {
        val price = dataSourceRepository.getProductPrice(productId, size)
        dataSourceRepository.updateProductPrice(price.copy(count = price.count - 1), productId)
        return dataSourceRepository.getProduct(productId)
    }

    override fun getShoppingCartInfo(): ShoppingCartInfo {
        val productsAdded = dataSourceRepository.getProductsAdded()
        val specialProduct = dataSourceRepository.getSpecialProduct()
        return ShoppingCartInfo(productsAdded, specialProduct, 0F, productsAdded.firstOrNull()?.prices?.firstOrNull()?.currency ?: "€")
    }

    override fun resetProductsCount() {
        dataSourceRepository.resetProductsCount()
    }

    override fun addSpecialProduct(specialProduct: SpecialProduct) {
        dataSourceRepository.addSpecialProduct(specialProduct)
    }

    private fun getProductsFromApiAndUpdateDataSource(userType: UserType) = apiRepository.getProducts(userType).apply {
        either(onSuccess = { dataSourceRepository.insertProducts(it) })
    }

}