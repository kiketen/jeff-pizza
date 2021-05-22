package com.jeff.pizza.cart.domain.usecase

import com.jeff.pizza.cart.domain.model.ShoppingCartInfo
import com.jeff.pizza.cart.domain.model.SpecialProduct
import com.jeff.pizza.core.domain.model.base.Either
import com.jeff.pizza.core.domain.model.base.Failure
import com.jeff.pizza.core.domain.model.products.Product
import com.jeff.pizza.core.domain.resource.products.ProductsResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetShoppingCartInfoUseCase {
    suspend fun execute(): Either<Failure.NoData, ShoppingCartInfo>
}

class GetShoppingCartInfoUseCaseImpl @Inject constructor(
        private val productsResource: ProductsResource,
        private val dispatcher: CoroutineDispatcher): GetShoppingCartInfoUseCase {

    override suspend fun execute(): Either<Failure.NoData, ShoppingCartInfo> {
        return withContext(dispatcher) {
            val products = productsResource.getProductsAdded()
            if (products.isNullOrEmpty()) {
                Either.Left(Failure.NoData)
            } else {
                val shoppingCartInfo = ShoppingCartInfo(products, null, getTotalAmount(products, null))
                Either.Right(shoppingCartInfo)
            }
        }
    }

    private fun getTotalAmount(products: List<Product>, specialProduct: SpecialProduct?): Float {
        var totalAmount = 0F
        products.forEach { product ->
            product.prices.forEach {
                totalAmount = +it.count * it.amount
            }
        }
        return totalAmount
    }
}