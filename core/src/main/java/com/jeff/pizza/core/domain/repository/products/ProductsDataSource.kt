package com.jeff.pizza.core.domain.repository.products

import com.jeff.pizza.core.domain.model.base.Either
import com.jeff.pizza.core.domain.model.base.Failure
import com.jeff.pizza.core.domain.model.products.Price
import com.jeff.pizza.core.domain.model.products.Product
import com.jeff.pizza.core.domain.model.products.SpecialProduct


interface ProductsDataSource {
    fun getProducts(): Either<Failure.NoData, List<Product>>
    fun getProduct(productId: Long): Product
    fun getProductPrice(productId: Long, size: String): Price
    fun insertProducts(products: List<Product>)
    fun updateProductPrice(price: Price, productId: Long)
    fun getProductsAdded(): List<Product>
    fun resetProductsCount()
    fun addSpecialProduct(specialProduct: SpecialProduct)
    fun getSpecialProduct(): SpecialProduct?
}