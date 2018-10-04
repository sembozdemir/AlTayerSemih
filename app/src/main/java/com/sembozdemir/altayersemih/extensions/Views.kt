package com.sembozdemir.altayersemih.extensions

import android.os.Build
import android.support.annotation.LayoutRes
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.TextView
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

fun TextView.setHtml(htmlText: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        text = Html.fromHtml(htmlText, Html.FROM_HTML_MODE_COMPACT)
    } else {
        text = Html.fromHtml(htmlText)
    }
}

/**
 * Performs the given action when the view tree is about to be drawn.
 */
inline fun View.doOnPreDraw(crossinline action: (view: View) -> Unit) {
    val vto = viewTreeObserver
    vto.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
        override fun onPreDraw(): Boolean {
            action(this@doOnPreDraw)
            when {
                vto.isAlive -> vto.removeOnPreDrawListener(this)
                else -> viewTreeObserver.removeOnPreDrawListener(this)
            }
            return true
        }
    })
}