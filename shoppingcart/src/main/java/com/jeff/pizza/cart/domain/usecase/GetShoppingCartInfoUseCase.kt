package com.jeff.pizza.cart.domain.usecase

import com.jeff.pizza.cart.presentation.model.ShoppingCartInfoUI
import com.jeff.pizza.cart.presentation.model.toUI
import com.jeff.pizza.core.domain.model.base.Either
import com.jeff.pizza.core.domain.model.base.Failure
import com.jeff.pizza.core.domain.model.products.Product
import com.jeff.pizza.core.domain.model.products.SpecialProduct
import com.jeff.pizza.core.domain.resource.products.ProductsResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetShoppingCartInfoUseCase {
    suspend fun execute(): Either<Failure.NoData, ShoppingCartInfoUI>
}

class GetShoppingCartInfoUseCaseImpl @Inject constructor(
        private val productsResource: ProductsResource,
        private val dispatcher: CoroutineDispatcher): GetShoppingCartInfoUseCase {

    override suspend fun execute(): Either<Failure.NoData, ShoppingCartInfoUI> {
        return withContext(dispatcher) {
            val shoppingCartInfo = productsResource.getShoppingCartInfo()
            if (shoppingCartInfo.products.isNullOrEmpty()) {
                Either.Left(Failure.NoData)
            } else {
                Either.Right(shoppingCartInfo.copy(totalAmount = getTotalAmount(
                        shoppingCartInfo.products, shoppingCartInfo.specialProduct)
                ).toUI())
            }
        }
    }

    private fun getTotalAmount(products: List<Product>, specialProduct: SpecialProduct?): Float {
        var totalAmount = 0F
        products.forEach { product ->
            product.prices.forEach {
                totalAmount += it.count * it.amount
            }
        }
        specialProduct?.let {
            totalAmount += it.count * it.amount
        }
        return totalAmount
    }
}