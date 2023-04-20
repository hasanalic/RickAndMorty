package com.hasanalic.rickandmorty.util

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hasanalic.rickandmorty.R
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun Fragment.toast(msg: String) {
    Toast.makeText(requireContext(),msg, Toast.LENGTH_SHORT).show()
}


fun ImageView.downloadFromUrl(url: String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.ic_broken_image)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}

fun placeHolderProgressBar(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

fun String.dateFormat(format: String): String {
    val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        DateTimeFormatter.ofPattern(format, Locale("tr"))
    } else {
        throw Exception("Android sürümü Oreo'dan eski olduğu için DateTimeFormatter kullanılamaz.")
    }
    if (this.isNotEmpty()) {
        val date = ZonedDateTime.parse(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return formatter.format(date)
        } else {
            throw Exception("Android sürümü Oreo'dan eski olduğu için DateTimeFormatter kullanılamaz.")
        }
    }
    throw Exception("Null")
}