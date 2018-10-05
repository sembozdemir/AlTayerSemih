package com.sembozdemir.altayersemih.util

import com.sembozdemir.altayersemih.BuildConfig

object ImageUrl {

    fun forList(suffix: String) = "${BuildConfig.IMAGE_BASE_URL_FOR_LIST}$suffix"

    fun forDetail(suffix: String) = "${BuildConfig.IMAGE_BASE_URL_FOR_DETAIL}$suffix"

    fun forSwatch(suffix: String) = "${BuildConfig.IMAGE_BASE_URL_FOR_SWATCH}$suffix"
}