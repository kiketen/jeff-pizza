package com.jeff.pizza.core.presentation.extensions

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat


fun Context.getCompatDrawable(@DrawableRes drawable: Int) = ContextCompat.getDrawable(this, drawable)

fun Context.getCompatColor(@ColorRes color: Int) = ContextCompat.getColor(this, color)