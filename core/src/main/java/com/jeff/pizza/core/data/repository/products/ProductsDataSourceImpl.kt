package com.jeff.pizza.core.data.repository.products

import com.jeff.pizza.core.data.model.ProductAndPricesDaoModel
import com.jeff.pizza.core.data.model.toDao
import com.jeff.pizza.core.data.model.toDomain
import com.jeff.pizza.core.domain.model.base.Either
import com.jeff.pizza.core.domain.model.base.Failure
import com.jeff.pizza.core.domain.model.products.Price
import com.jeff.pizza.core.domain.model.products.Product
import com.jeff.pizza.core.domain.model.products.SpecialProduct
import com.jeff.pizza.core.domain.repository.products.ProductsDataSource
import javax.inject.Inject


class ProductsDataSourceImpl @Inject constructor(
        private val productsDAO: ProductsDAO,
        private val pricesDAO: PricesDAO
): ProductsDataSource {

    private var specialProduct: SpecialProduct? = null

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

    override fun getProductPrice(productId: Long, size: String): Price {
        return pricesDAO.getPrice(productId, size).toDomain()
    }

    override fun insertProducts(products: List<Product>) {
        productsDAO.insertProducts(products.toDao())
        products.forEach {
            pricesDAO.insertPrices(it.prices.toDao(it.id))
        }
    }

    override fun updateProductPrice(price: Price, productId: Long) {
        pricesDAO.updatePrice(price.toDao(productId))
    }

    override fun getProductsAdded(): List<Product> {
        return productsDAO.getProductsAdded().filterPricesAdded().toDomain()
    }

    override fun resetProductsCount() {
        pricesDAO.resetCount()
        specialProduct = null
    }

    override fun addSpecialProduct(specialProduct: SpecialProduct) {
        this.specialProduct = specialProduct
    }

    override fun getSpecialProduct(): SpecialProduct? {
        return specialProduct
    }

    private fun List<ProductAndPricesDaoModel>.filterPricesAdded(): List<ProductAndPricesDaoModel> {
        return map {
            it.copy(prices = it.prices.filter { price -> price.count > 0 })
        }
    }
}