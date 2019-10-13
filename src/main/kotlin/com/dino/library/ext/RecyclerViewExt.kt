package com.dino.library.ext

import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dino.library.ui.SimpleRecyclerView

@Suppress("UNCHECKED_CAST")
@BindingAdapter("itemLayout", "items")
fun RecyclerView.setSimpleAdapter(
    @LayoutRes adapterItemId: Int,
    items: List<Any>?
) {
    /**
     * itemLayout="@{@layout/item_main}"
     * items="@{vm.liveMainItem}"
     */
    val simpleAdapter =
        this.adapter as? SimpleRecyclerView.Adapter
            ?: SimpleRecyclerView.Adapter(adapterItemId).also {
                this.adapter = it
            }
    simpleAdapter.replaceAll(items)
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("itemLayout", "diffCallback", "items")
fun RecyclerView.setSimpleListAdapter(
    @LayoutRes itemLayout: Int,
    diffCallback: DiffUtil.ItemCallback<Any>,
    items: List<Any>?
) {
    /**
     * diffCallback="@{(Object)MainItem.Companion}"
     * itemLayout="@{@layout/item_main}"
     * items="@{vm.liveMainItem}"
     */
    val simpleListAdapter =
        this.adapter as? SimpleRecyclerView.ListAdapter
            ?: SimpleRecyclerView.ListAdapter(itemLayout, diffCallback).also {
                this.adapter = it
            }
    simpleListAdapter.submitList(items)
}
