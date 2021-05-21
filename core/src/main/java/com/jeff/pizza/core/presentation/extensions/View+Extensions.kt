package com.jeff.pizza.core.presentation.extensions

import android.view.View

private const val VISIBILITY_ANIMATION_DURATION = 350L

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.isVisible(): Boolean {
    return visibility == View.VISIBLE
}

fun View.switchVisibilityAnimated(visible: Boolean, duration: Long = VISIBILITY_ANIMATION_DURATION) {
    this.animate().cancel()
    when {
        visible -> fadeIn(duration = duration)
        !visible -> fadeOut(duration = duration)
    }
}