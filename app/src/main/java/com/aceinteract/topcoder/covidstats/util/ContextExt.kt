package com.aceinteract.topcoder.covidstats.util

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.aceinteract.topcoder.covidstats.R
import com.google.android.material.snackbar.Snackbar

fun Context.showSnackBar(
    rootView: View,
    text: String,
    isError: Boolean = false,
    duration: Int = Snackbar.LENGTH_SHORT
) {
    val snackBar = Snackbar.make(rootView, text, duration)
    val snackBarLayout = snackBar.view as Snackbar.SnackbarLayout
    if (isError) snackBarLayout.setBackgroundColor(
        getColorCompat(android.R.color.holo_red_dark)
    ) else snackBarLayout.setBackgroundColor(
        ContextCompat.getColor(this, R.color.color_accent)
    )
    snackBarLayout.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        .setTextColor(getColorCompat(android.R.color.white))

    snackBar.show()
}

fun Context.getColorCompat(@ColorRes id: Int) = ContextCompat.getColor(this, id)