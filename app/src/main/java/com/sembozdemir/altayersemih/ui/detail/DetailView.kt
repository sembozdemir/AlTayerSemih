package com.sembozdemir.altayersemih.ui.detail

import com.sembozdemir.altayersemih.core.BaseView
import com.sembozdemir.altayersemih.network.model.Product

interface DetailView : BaseView {
    fun showProduct(product: Product)
}