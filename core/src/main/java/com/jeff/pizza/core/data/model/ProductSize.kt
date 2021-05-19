package com.jeff.pizza.core.data.model


enum class ProductSize(val value: String? = null) {
    S("S"), M("M"), L("L"), XL("XL"), UNKNOWN;


    companion object {
        fun fromStringName(name: String): ProductSize =
                values().find { it.value == name } ?: UNKNOWN
    }

}