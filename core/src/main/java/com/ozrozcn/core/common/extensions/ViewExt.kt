package com.ozrozcn.core.common.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(source: Any) {
    Glide.with(this).load(source).into(this)
}
