package com.mureed.app.utils

import android.graphics.Color
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.mureed.app.utils.Constants.TEXT_TRY_AGAIN

fun View.showErrorSnackBar(error: String, retry: () -> Unit) {
    Snackbar.make(this, error, Snackbar.LENGTH_LONG)
        .setBackgroundTint(Color.RED)
        .setTextColor(Color.WHITE)
        .setActionTextColor(Color.WHITE)
        .setAction(TEXT_TRY_AGAIN) {
        retry()
    }
        .show()
}