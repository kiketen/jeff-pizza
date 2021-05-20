package com.jeff.pizza.core.data.repository.products

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jeff.pizza.core.data.model.ProductApiModel

@Dao
interface ProductsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProducts(usersEntity: List<ProductApiModel>)

    @Query("SELECT * FROM ProductApiModel")
    fun getProducts(): List<ProductApiModel>

    @Query("SELECT * FROM ProductApiModel WHERE id = :productId")
    fun getProduct(productId: Long): ProductApiModel

    @Update
    fun updateProduct(productApiModel: ProductApiModel)
}
