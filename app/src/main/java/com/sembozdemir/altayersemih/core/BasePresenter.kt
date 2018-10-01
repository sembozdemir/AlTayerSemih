package com.sembozdemir.altayersemih.core

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

abstract class BasePresenter<V: BaseView> : MvpBasePresenter<V>()