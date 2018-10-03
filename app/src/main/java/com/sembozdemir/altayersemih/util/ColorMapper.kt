package com.sembozdemir.altayersemih.util

import com.sembozdemir.altayersemih.network.model.ColorValue

interface ColorMapper {

    fun getHex(name: String): String?

    fun getImage(name: String): String?

    fun hasHex(name: String): Boolean

    fun hasImage(name: String): Boolean

    fun setColorValues(colorValues: List<ColorValue>)
}