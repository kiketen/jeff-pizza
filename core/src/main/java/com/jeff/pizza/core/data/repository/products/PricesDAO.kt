package com.jeff.pizza.core.data.repository.products

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jeff.pizza.core.data.model.PriceDaoModel

@Dao
interface PricesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPrices(prices: List<PriceDaoModel>)

    @Update
    fun updatePrice(priceDaoModel: PriceDaoModel)

    @Query("UPDATE PriceDaoModel SET count = 0")
    fun resetCount()

    @Query("SELECT * FROM PriceDaoModel WHERE productId = :productId AND size = :size")
    fun getPrice(productId: Long, size: String): PriceDaoModel
}
