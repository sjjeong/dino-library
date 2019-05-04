package com.dino.library.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dino.library.ui.SimpleRecyclerView

@Suppress("UNCHECKED_CAST")
@BindingAdapter("replaceAll")
fun RecyclerView.replaceAll(list: List<Any>?) {
    (this.adapter as? SimpleRecyclerView.Adapter<Any, *>)?.run {
        replaceAll(list)
        notifyDataSetChanged()
    }
    (this.adapter as? SimpleRecyclerView.ListAdapter<Any, *>)?.run {
        replaceAll(list)
        notifyDataSetChanged()
    }
}
