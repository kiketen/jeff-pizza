package com.jeff.pizza.core.presentation.model

sealed class Failure {
    object NoData: Failure()
    object UnknownError: Failure()
}