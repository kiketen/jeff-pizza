package com.jeff.pizza

import com.jeff.pizza.core.domain.model.base.Either
import com.jeff.pizza.core.domain.model.base.Failure
import com.jeff.pizza.core.domain.model.products.Price
import com.jeff.pizza.core.domain.model.products.Product
import com.jeff.pizza.core.domain.model.user.UserType
import com.jeff.pizza.core.domain.repository.products.ProductsApi
import com.jeff.pizza.core.domain.repository.products.ProductsDataSource
import com.jeff.pizza.core.domain.resource.products.ProductsResourceImpl
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given
import org.mockito.kotlin.verify


@RunWith(MockitoJUnitRunner::class)
class ProductsResourceTest {

    @Mock
    private lateinit var dataSourceRepository: ProductsDataSource

    @Mock
    private lateinit var apiRepository: ProductsApi

    private lateinit var productsResource: ProductsResourceImpl

    private val productId = 1L
    private val product = Product(
            id = productId,
            name = "name",
            content = "content",
            imageUrl = "imageUrl",
            prices = listOf(Price(
                    size = "M",
                    amount = 0F,
                    customerSatisfaction = 0,
                    count = 2
            ), Price(
                    size = "L",
                    amount = 0F,
                    customerSatisfaction = 0,
                    count = 0
            ))
    )

    private val increasedProduct = Product(
            id = productId,
            name = "name",
            content = "content",
            imageUrl = "imageUrl",
            prices = listOf(Price(
                    size = "M",
                    amount = 0F,
                    customerSatisfaction = 0,
                    count = 3
            ), Price(
                    size = "L",
                    amount = 0F,
                    customerSatisfaction = 0,
                    count = 0
            ))
    )

    private val decreasedProduct = Product(
            id = productId,
            name = "name",
            content = "content",
            imageUrl = "imageUrl",
            prices = listOf(Price(
                    size = "M",
                    amount = 0F,
                    customerSatisfaction = 0,
                    count = 1
            ), Price(
                    size = "L",
                    amount = 0F,
                    customerSatisfaction = 0,
                    count = 0
            ))
    )

    private val products = listOf(product)
    private val userType = UserType.SINGLE

    @Before
    fun init() {
        productsResource = ProductsResourceImpl(dataSourceRepository, apiRepository)
    }

    @Test
    fun `Given products from api When call getProducts Then return products`() {
        given(apiRepository.getProducts(userType)).willReturn(Either.Right(products))

        productsResource.getProducts(refresh = true, userType = userType)

        verify(apiRepository).getProducts(userType)
        verify(dataSourceRepository).insertProducts(products)
    }

    @Test
    fun `Given error from api When call getProducts Then return error`() {
        given(apiRepository.getProducts(userType)).willReturn(Either.Left(Failure.UnknownError))

        productsResource.getProducts(refresh = true, userType = userType)

        verify(apiRepository).getProducts(userType)
    }

    @Test
    fun `Given products from dataSource When call getProducts Then return products`() {
        given(dataSourceRepository.getProducts(userType)).willReturn(Either.Right(products))

        productsResource.getProducts(refresh = false, userType = userType)

        verify(dataSourceRepository).getProducts(userType)
    }

    @Test
    fun `Given noData from dataSource When call getProducts Then call api`() {
        given(apiRepository.getProducts(userType)).willReturn(Either.Right(products))
        given(dataSourceRepository.getProducts(userType)).willReturn(Either.Left(Failure.NoData))

        productsResource.getProducts(refresh = false, userType = userType)

        verify(dataSourceRepository).getProducts(userType)
        verify(apiRepository).getProducts(userType)
    }

    @Test
    fun `Given product When call addProduct Then call update with increased product`() {
        given(dataSourceRepository.getProduct(productId)).willReturn(product)

        productsResource.addProduct(productId, "M")

        verify(dataSourceRepository).updateProduct(increasedProduct)
    }

    @Test
    fun `Given product When call removeProduct Then call update with decreased product`() {
        given(dataSourceRepository.getProduct(productId)).willReturn(product)

        productsResource.removeProduct(productId, "M")

        verify(dataSourceRepository).updateProduct(decreasedProduct)
    }
}