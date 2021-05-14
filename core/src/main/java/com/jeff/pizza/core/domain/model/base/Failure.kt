package com.jeff.pizza.core.domain.model.base

sealed class Failure {
    object NoData: Failure()
    object UnknownError: Failure()
}