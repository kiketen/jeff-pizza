package com.jeff.pizza.core.presentation.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ImageLoader(private val context: Context) {

    fun loadImage(
            imageUrl: String?,
            view: ImageView,
            placeholder: Drawable?,
            error: Drawable?
    ) {

        Glide.with(context)
                .load(imageUrl)
                .placeholder(placeholder)
                .error(error ?: placeholder)
                .into(view)
    }

    fun loadRoundedImage(
            imageUrl: String,
            view: ImageView
    ) {
        Glide.with(context)
                .asBitmap()
                .load(imageUrl)
                .apply(RequestOptions.fitCenterTransform())
                .apply(RequestOptions.circleCropTransform())
                .into(view)
    }
}
