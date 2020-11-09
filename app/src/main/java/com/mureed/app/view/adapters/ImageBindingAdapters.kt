package com.mureed.app.view.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.mureed.app.R
import com.mureed.app.view.widget.RoundCornerTransformation
import com.squareup.picasso.Picasso
import kotlin.math.max

object ImageBindingAdapters {

    @JvmStatic
    @BindingAdapter("coverUrl")
    fun loadCover(view: ImageView, url: String?) {
        if (!url.isNullOrEmpty()) {
            val width = view.resources.getDimensionPixelSize(R.dimen.series_item_width)
            val height = view.resources.getDimensionPixelSize(R.dimen.series_item_height)
            val radius = view.context.resources.getDimensionPixelSize(R.dimen.cover_radius)
            Picasso.get().load(url)
                .error(R.drawable.image_placeholder)
                .placeholder(R.drawable.image_placeholder)
                .centerCrop()
                .resize(width, height)
                .transform(RoundCornerTransformation(radius.toFloat()))
                .into(view)
        } else view.setImageResource(R.drawable.image_placeholder)
    }


    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String?) {
        if (!url.isNullOrEmpty()) {
            val width = view.resources.getDimensionPixelSize(R.dimen.course_width)
            val height = view.resources.getDimensionPixelSize(R.dimen.course_height)
            val radius = view.context.resources.getDimensionPixelSize(R.dimen.cover_radius)
            Picasso.get().load(url)
                .error(R.drawable.image_placeholder)
                .placeholder(R.drawable.image_placeholder)
                .centerCrop()
                .resize(width, height)
                .transform(RoundCornerTransformation(radius.toFloat()))
                .into(view)
        } else view.setImageResource(R.drawable.image_placeholder)
    }


    @JvmStatic
    @BindingAdapter("circleImageUrl")
    fun loadCircularImage(view: ImageView, url: String?) {
        if (!url.isNullOrEmpty())
            view.post {
                val radius = max(view.width, view.height) / 2
                Picasso.get().load(url)
                    .error(R.drawable.image_placeholder_circle)
                    .placeholder(R.drawable.image_placeholder_circle)
                    .centerCrop()
                    .resize(view.width, view.height)
                    .transform(RoundCornerTransformation(radius.toFloat()))
                    .into(view)
            }
        else
            view.setImageResource(R.drawable.image_placeholder_circle)
    }

}