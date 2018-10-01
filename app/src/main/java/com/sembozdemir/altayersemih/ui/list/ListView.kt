package com.sembozdemir.altayersemih.ui.list

import com.sembozdemir.altayersemih.core.BaseView
import com.sembozdemir.altayersemih.network.model.Hit

interface ListView : BaseView {

    fun showCategoryName(categoryName: String)
    fun populateList(hits: List<Hit>?)
}