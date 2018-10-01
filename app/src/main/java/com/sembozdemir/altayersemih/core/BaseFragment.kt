package com.sembozdemir.altayersemih.core

import android.content.Context
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment<V : BaseView, P : BasePresenter<V>> : MvpFragment<V, P>(), BaseView {

    @LayoutRes
    abstract fun getLayoutResId(): Int

    @CallSuper
    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResId(), container, false)
    }

}