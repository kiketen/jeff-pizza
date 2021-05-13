package com.jeff.pizza.core.model

sealed class Failure {
    object NoData: Failure()
    object UnknownError: Failure()
}