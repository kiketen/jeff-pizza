package com.jeff.pizza.core.presentation.model

sealed class Either<out L, out R> {

    data class Left<out L>(val a: L): Either<L, Nothing>()
    data class Right<out R>(val b: R): Either<Nothing, R>()

    val isRight get() = this is Right<R>
    val isLeft get() = this is Left<L>

    fun <L> left(a: L) = Left(a)
    fun <R> right(b: R) = Right(b)

    fun either(onError: (L) -> Any = {}, onSuccess: (R) -> Any = {}): Any =
            when (this) {
                is Left -> onError(a)
                is Right -> onSuccess(b)
            }
}
