package com.jeff.pizza.core.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface DelayUseCase {
    suspend fun execute(milliseconds: Long)
}

class DelayUseCaseImpl
@Inject constructor(private val dispatcher: CoroutineDispatcher): DelayUseCase {

    override suspend fun execute(milliseconds: Long) {
        withContext(dispatcher) {
            delay(milliseconds)
        }
    }
}