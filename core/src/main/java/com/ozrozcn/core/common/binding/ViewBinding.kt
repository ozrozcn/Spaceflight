package com.ozrozcn.core.common.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object ViewBinding {
    @JvmStatic
    @BindingAdapter(value = ["itemDecoration"])
    fun setItemDecoration(view: RecyclerView, itemDecoration: RecyclerView.ItemDecoration) {
        view.removeItemDecoration(itemDecoration)
        view.addItemDecoration(itemDecoration)
    }
}