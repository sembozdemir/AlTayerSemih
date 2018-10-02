package com.sembozdemir.altayersemih.extensions

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View = LayoutInflater.from(context).inflate(layoutRes, this, false)

fun ImageView.setImageUrl(url: String, init: (RequestCreator.() -> RequestCreator)? = null) {
    val requestCreator = Picasso.get().load(url)
    if (init != null) requestCreator.init()
    requestCreator.into(this)
}

fun View.setVisible(visible: Boolean, falseVisibility: Int = View.GONE) {
    visibility = if (visible) View.VISIBLE else falseVisibility
}
