package com.jeff.pizza.core.data.repository.products

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jeff.pizza.core.data.model.PriceApiModel

class ProductConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToPrice(data: String): PriceApiModel {
        val name = object: TypeToken<PriceApiModel>() {}.type
        return gson.fromJson(data, name)
    }

    @TypeConverter
    fun priceToString(price: PriceApiModel): String = gson.toJson(price)

    @TypeConverter
    fun stringToPrices(data: String): List<PriceApiModel>? {
        val name = object: TypeToken<List<PriceApiModel>?>() {}.type
        return gson.fromJson(data, name)
    }

    @TypeConverter
    fun pricesToString(prices: List<PriceApiModel>?): String = gson.toJson(prices)
}