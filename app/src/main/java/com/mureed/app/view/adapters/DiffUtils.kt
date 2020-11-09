package com.mureed.app.view.adapters

import androidx.recyclerview.widget.DiffUtil
import com.mureed.app.data.model.Category
import com.mureed.app.data.model.Channel
import com.mureed.app.data.model.Media

val categoriesCallback = object : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.name.equals(newItem.name, ignoreCase = true)
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}

val mediaCallback = object : DiffUtil.ItemCallback<Media>() {
    override fun areItemsTheSame(oldItem: Media, newItem: Media): Boolean {
        return oldItem.title.equals(newItem.title, ignoreCase = true)
    }

    override fun areContentsTheSame(oldItem: Media, newItem: Media): Boolean {
        return oldItem == newItem
    }
}

val channelCallback = object : DiffUtil.ItemCallback<Channel>() {
    override fun areItemsTheSame(oldItem: Channel, newItem: Channel): Boolean {
        return oldItem.title.equals(newItem.title, ignoreCase = true)
    }

    override fun areContentsTheSame(oldItem: Channel, newItem: Channel): Boolean {
        return oldItem == newItem
    }
}