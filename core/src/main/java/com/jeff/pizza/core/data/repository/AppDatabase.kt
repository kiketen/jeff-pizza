package com.jeff.pizza.core.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jeff.pizza.core.data.model.PriceDaoModel
import com.jeff.pizza.core.data.model.ProductDaoModel
import com.jeff.pizza.core.data.repository.products.PricesDAO
import com.jeff.pizza.core.data.repository.products.ProductConverter
import com.jeff.pizza.core.data.repository.products.ProductsDAO

@Database(entities = [
    ProductDaoModel::class,
    PriceDaoModel::class], version = 1)
@TypeConverters(ProductConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun productsDAO(): ProductsDAO
    abstract fun pricesDAO(): PricesDAO
}