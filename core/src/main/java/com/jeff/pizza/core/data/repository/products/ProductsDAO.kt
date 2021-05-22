package com.jeff.pizza.core.data.repository.products

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.jeff.pizza.core.data.model.ProductAndPricesDaoModel
import com.jeff.pizza.core.data.model.ProductDaoModel

@Dao
interface ProductsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProducts(products: List<ProductDaoModel>)

    @Transaction
    @Query("SELECT * FROM ProductDaoModel")
    fun getProducts(): List<ProductAndPricesDaoModel>

    @Transaction
    @Query("SELECT * FROM ProductDaoModel product, PriceDaoModel price WHERE id = :productId ORDER BY price.customerSatisfaction")
    fun getProduct(productId: Long): ProductAndPricesDaoModel

    @Transaction
    @Query("SELECT * FROM ProductDaoModel WHERE (SELECT MAX(count) FROM PriceDaoModel WHERE productId = id) > 0")
    fun getProductsAdded(): List<ProductAndPricesDaoModel>?
}
