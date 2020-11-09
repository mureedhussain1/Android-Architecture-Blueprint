package com.mureed.app.view.widget

import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class SpacingDivider(@DimenRes private val spacingDimen: Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val spacing = parent.context.resources.getDimensionPixelSize(spacingDimen) / 2
        parent.clipToPadding=false
        parent.setPadding(spacing, spacing, spacing, 0)
        outRect.set(spacing, spacing, spacing, spacing)
    }

}