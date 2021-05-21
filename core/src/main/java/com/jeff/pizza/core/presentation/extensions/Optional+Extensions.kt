package com.jeff.pizza.core.presentation.extensions


fun <T> T?.isNotNull(): Boolean =
        !this.isNull()

fun <T> T?.isNull(): Boolean =
        this == null