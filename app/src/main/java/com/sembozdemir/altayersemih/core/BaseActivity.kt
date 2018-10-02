package com.sembozdemir.altayersemih.core

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.v7.widget.Toolbar
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.sembozdemir.altayersemih.R
import dagger.android.AndroidInjection

abstract class BaseActivity<V : BaseView, P : BasePresenter<V>> : MvpActivity<V, P>(), BaseView {

    @LayoutRes
    abstract fun getLayoutResId(): Int

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(getLayoutResId())
    }

    fun enableHomeAsUp(toolbar: Toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener { finish() }
    }
}