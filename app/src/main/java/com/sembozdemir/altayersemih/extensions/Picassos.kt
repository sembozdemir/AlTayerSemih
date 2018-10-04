package com.sembozdemir.altayersemih.extensions

import android.widget.ImageView


fun com.squareup.picasso.RequestCreator.into(target: ImageView, func: CallbackDSL.() -> Unit) {
    val callback = CallbackDSL()
    callback.func()
    into(target, callback)
}

class CallbackDSL : com.squareup.picasso.Callback {

    private var _onSuccess: (() -> Unit)? = null
    private var _onError: ((e: Exception?) -> Unit)? = null

    override fun onSuccess() {
        _onSuccess?.invoke()
    }

    fun onSuccess(func: () -> Unit) {
        _onSuccess = func
    }

    override fun onError(e: Exception?) {
        _onError?.invoke(e)
    }

    fun onError(func: (e: Exception?) -> Unit) {
        _onError = func
    }
}
