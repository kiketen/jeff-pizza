package com.jeff.pizza.core.presentation.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.jeff.pizza.core.R
import com.jeff.pizza.core.databinding.BrandButtonBinding
import com.jeff.pizza.core.presentation.extensions.switchVisibilityAnimated

class BrandButton @JvmOverloads
constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
        defStyleRes: Int = R.style.Base_Widget_AppCompat_Button
): FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private var binding: BrandButtonBinding =
            BrandButtonBinding.inflate(LayoutInflater.from(context), this)
    private val buttonText
        get() = binding.buttonText
    private val buttonLoader
        get() = binding.buttonLoader

    init {
        attrs?.let {
            setAttributes(context, it)
        }
    }

    var isLoading: Boolean = false
        set(value) {
            isClickable = !value
            buttonLoader.switchVisibilityAnimated(visible = value)
            buttonText.switchVisibilityAnimated(visible = !value)
            field = value
            refreshDrawableState()
        }

    private fun setAttributes(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BrandButton)

        buttonText.text = typedArray.getString(R.styleable.BrandButton_android_text).orEmpty()
        isEnabled = typedArray.getBoolean(R.styleable.BrandButton_android_enabled, isEnabled)

        setBackgroundResource(R.drawable.bkg_main_button)

        typedArray.recycle()
    }
}