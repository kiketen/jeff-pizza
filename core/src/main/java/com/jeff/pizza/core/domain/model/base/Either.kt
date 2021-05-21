package com.jeff.pizza.core.domain.model.base

sealed class Either<out L, out R> {

    data class Left<out L>(val a: L): Either<L, Nothing>()
    data class Right<out R>(val b: R): Either<Nothing, R>()

    val isRight get() = this is Right<R>
    val isLeft get() = this is Left<L>

    val rightValue get() = (this as Right).b

    fun either(onError: (L) -> Any = {}, onSuccess: (R) -> Any = {}): Any =
            when (this) {
                is Left -> onError(a)
                is Right -> onSuccess(b)
            }
}
