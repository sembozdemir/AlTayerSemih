package com.sembozdemir.altayersemih.customview

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View


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