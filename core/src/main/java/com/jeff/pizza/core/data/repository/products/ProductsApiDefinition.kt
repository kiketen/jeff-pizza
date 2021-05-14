package com.jeff.pizza.core.data.repository.products

import com.jeff.pizza.core.data.model.ProductApiModel
import retrofit2.Call
import retrofit2.http.GET


interface ProductsApiDefinition {

    @GET("raw")
    fun getProducts(): Call<List<ProductApiModel>>

}