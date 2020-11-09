package com.mureed.app.view.adapters

import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mureed.app.R
import com.mureed.app.data.model.Channel
import com.mureed.app.data.model.Media

object ChannelBindingAdapters {

    @JvmStatic
    @BindingAdapter("recyclerViewItems", "itemView", requireAll = false)
    fun setAdapter(
        recyclerView: RecyclerView,
        recyclerViewItems: List<Media>?,
        @LayoutRes itemView: Int?
    ) {
        if (recyclerViewItems.isNullOrEmpty()) {
            recyclerView.visibility = View.GONE
            return
        }
        recyclerView.visibility = View.VISIBLE
        val adapter = BindingRecyclerViewAdapter(mediaCallback, itemView ?: R.layout.item_media)
        recyclerView.adapter = adapter
        adapter.submitList(recyclerViewItems)
    }


    @JvmStatic
    @BindingAdapter("episodesCount")
    fun setEpisodesCount(view: TextView, channel: Channel?) {
        val counts = if (!channel?.series.isNullOrEmpty()) channel!!.series!!.size
        else channel?.latestMedia?.size ?: 0
        val text = "$counts episodes"
        view.text = text
    }

}