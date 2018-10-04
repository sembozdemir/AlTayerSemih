package com.sembozdemir.altayersemih.customview

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import timber.log.Timber

/**
 * ViewPager that supports click event.
 *
 * Fix for PhotoView issue (see: https://github.com/chrisbanes/PhotoView/issues/31)
 */
class ClickableViewPager @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null
) : ViewPager(context, attrs) {

    private var onClickListener: View.OnClickListener? = null

    init {
        val onSingleTapConfirmedGestureDetector = GestureDetector(context, OnSingleTapConfirmedGestureListener(this))
        setOnTouchListener { v, event ->
            onSingleTapConfirmedGestureDetector.onTouchEvent(event)
            false
        }
    }

    override fun setOnClickListener(onClickListener: View.OnClickListener?) {
        this.onClickListener = onClickListener
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        // Fix for PhotoView issue (see: https://github.com/chrisbanes/PhotoView/issues/31)
        return try {
            super.onInterceptTouchEvent(ev)
        } catch (e: IllegalArgumentException) {
            Timber.w(e)
            false
        }

    }

    private inner class OnSingleTapConfirmedGestureListener(
            private val view: View
    ) : GestureDetector.SimpleOnGestureListener() {

        override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
            if (onClickListener != null) {
                onClickListener?.onClick(view)
            }
            return true
        }
    }
}