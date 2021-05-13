package com.jeff.pizza.utils

abstract class Instruction {

    abstract val description: String

    abstract fun checkCondition(): Boolean
}