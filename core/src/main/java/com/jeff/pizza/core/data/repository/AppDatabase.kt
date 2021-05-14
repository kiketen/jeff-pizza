package com.jeff.pizza.core.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jeff.pizza.core.data.model.ProductApiModel
import com.jeff.pizza.core.data.repository.products.ProductConverter
import com.jeff.pizza.core.data.repository.products.ProductsDAO

@Database(entities = [ProductApiModel::class], version = 1)
@TypeConverters(ProductConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun productsDAO(): ProductsDAO
}