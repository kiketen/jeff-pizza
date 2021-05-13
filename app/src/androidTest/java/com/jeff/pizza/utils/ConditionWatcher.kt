package com.jeff.pizza.utils

import java.util.concurrent.TimeoutException

class ConditionWatcher private constructor() {
    companion object {

        private const val DEFAULT_TIMEOUT_LIMIT = 1000 * 6
        private const val DEFAULT_INTERVAL = 250
        private const val SECONDS_TO_MILLIS = 1000

        @Throws(TimeoutException::class)
        fun waitForCondition(
                instruction: Instruction,
                watchInterval: Int = DEFAULT_INTERVAL,
                timeOutLimit: Int = DEFAULT_TIMEOUT_LIMIT
        ) {

            var elapsedTime = 0
            var conditionMet = false

            do {
                if (instruction.checkCondition()) {
                    conditionMet = true
                } else {
                    elapsedTime += watchInterval
                    Thread.sleep(watchInterval.toLong())
                }

                if (elapsedTime >= timeOutLimit) {
                    break
                }
            } while (!conditionMet)

            if (!conditionMet) {
                val message = "${instruction.description} - took more than ${timeOutLimit / SECONDS_TO_MILLIS} seconds. Test stopped."
                throw TimeoutException(message)
            }
        }
    }
}
