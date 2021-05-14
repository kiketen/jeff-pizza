package com.jeff.pizza.core.data.extension

import com.jeff.pizza.core.domain.model.base.Either
import com.jeff.pizza.core.domain.model.base.Failure
import retrofit2.Call


fun <T, R> Call<T>.make(transform: (T) -> R): Either<Failure, R> {
    return try {
        val response = execute()
        when (response.isSuccessful) {
            true -> Either.Right(transform((response.body()!!)))
            false -> Either.Left(Failure.UnknownError)
        }
    } catch (exception: Throwable) {
        Either.Left(Failure.UnknownError)
    } catch (exception: Exception) {
        Either.Left(Failure.UnknownError)
    }
}