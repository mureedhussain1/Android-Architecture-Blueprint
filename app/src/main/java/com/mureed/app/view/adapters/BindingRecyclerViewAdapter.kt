package com.mureed.app.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class BindingRecyclerViewAdapter<T>(
    diffUtil: DiffUtil.ItemCallback<T>,
    @LayoutRes private val itemLayout: Int
) : ListAdapter<T, BindingRecyclerViewHolder<T>>(diffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingRecyclerViewHolder<T> {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            itemLayout,
            parent,
            false
        )
        return BindingRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingRecyclerViewHolder<T>, position: Int) {
        holder.bind(getItem(position))
    }
}