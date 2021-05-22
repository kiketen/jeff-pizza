package com.jeff.pizza.core.data.repository.products

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jeff.pizza.core.data.model.PriceDaoModel

class ProductConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToPrice(data: String): PriceDaoModel {
        val name = object: TypeToken<PriceDaoModel>() {}.type
        return gson.fromJson(data, name)
    }

    @TypeConverter
    fun priceToString(price: PriceDaoModel): String = gson.toJson(price)

    @TypeConverter
    fun stringToPrices(data: String): List<PriceDaoModel>? {
        val name = object: TypeToken<List<PriceDaoModel>?>() {}.type
        return gson.fromJson(data, name)
    }

    @TypeConverter
    fun pricesToString(prices: List<PriceDaoModel>?): String = gson.toJson(prices)
}