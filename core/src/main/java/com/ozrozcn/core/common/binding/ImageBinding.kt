package com.ozrozcn.core.common.binding

import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import com.ozrozcn.core.common.extensions.loadImage

object ImageBinding {

    @JvmStatic
    @BindingAdapter(value = ["imageSource"])
    fun loadImage(imageView: ImageView, imageSource: Any? = null) {
        imageSource?.apply {
            imageView.loadImage(imageSource)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["imageTint"])
    fun changeImageTint(imageView: ImageView, @ColorInt color: Int) {
        imageView.setColorFilter(color)
    }
}