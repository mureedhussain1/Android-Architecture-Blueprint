package com.mureed.app.view.widget

import android.graphics.*
import com.squareup.picasso.Transformation

class RoundCornerTransformation(private val radius: Float) : Transformation {

    override fun key(): String = "rounded_corner_image"

    override fun transform(sourceBitmap: Bitmap): Bitmap {
        val bitmap = Bitmap.createBitmap(sourceBitmap.width, sourceBitmap.height, sourceBitmap.config)
        val canvas = Canvas(bitmap)

        val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG)
        val bitmapShader = BitmapShader(sourceBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.shader = bitmapShader

        val rectF = RectF(0.0f, 0.0f, sourceBitmap.width.toFloat(), sourceBitmap.height.toFloat())
        canvas.drawRoundRect(rectF, radius, radius, paint)
        sourceBitmap.recycle()

        return bitmap
    }
}