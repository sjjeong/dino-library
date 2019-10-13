package com.dino.library.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.googry.dinolibrary.BR
import com.googry.dinolibrary.BuildConfig

abstract class SimpleRecyclerView {

    class ListAdapter(
        @LayoutRes private val layoutRes: Int,
        diffCallback: DiffUtil.ItemCallback<Any>
    ) : androidx.recyclerview.widget.ListAdapter<Any, ViewHolder>(diffCallback) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(layoutRes = layoutRes, parent = parent)

        override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.onBindViewHolder(getItem(position))
    }

    open class Adapter(
        @LayoutRes private val layoutRes: Int
    ) : RecyclerView.Adapter<ViewHolder>() {

        protected open val items = mutableListOf<Any>()

        open fun replaceAll(items: List<Any>?) {
            this.items.run {
                clear()
                items?.let {
                    addAll(it)
                }
            }
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(layoutRes = layoutRes, parent = parent)

        override fun getItemCount() =
            items.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.onBindViewHolder(items[position])

    }

    open class ViewHolder(
        @LayoutRes layoutRes: Int,
        parent: ViewGroup?
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent?.context)
            .inflate(layoutRes, parent, false)
    ) {

        protected val binding: ViewDataBinding = DataBindingUtil.bind(itemView)!!

        open fun onBindViewHolder(item: Any?) {
            try {
                binding.run {
                    setVariable(BR.item, item)
                    executePendingBindings()
                }
            } catch (e: Exception) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace()
                }
            }
        }
    }

}