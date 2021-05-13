package com.jeff.pizza.core.presentation.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.jeff.pizza.core.presentation.utils.ImageLoader


fun ImageView.loadImage(
        imageUrl: String?,
        @DrawableRes placeholder: Int? = null,
        @DrawableRes error: Int? = null
) {

    ImageLoader(context).loadImage(
            imageUrl = imageUrl,
            view = this,
            placeholder = placeholder?.let { context.getCompatDrawable(it) },
            error = error?.let { context.getCompatDrawable(it) }
    )

}

fun ImageView.loadRoundedImage(imageUrl: String) {

    ImageLoader(context).loadRoundedImage(
            imageUrl = imageUrl,
            view = this
    )

}
