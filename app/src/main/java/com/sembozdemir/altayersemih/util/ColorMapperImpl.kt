package com.sembozdemir.altayersemih.util

import com.sembozdemir.altayersemih.network.model.ColorValue
import javax.inject.Singleton

@Singleton
class ColorMapperImpl : ColorMapper {

    private var colorMap: HashMap<String, ColorValue> = HashMap()

    override fun getHex(name: String): String? {
        return colorMap[name]?.hex
    }

    override fun getImage(name: String): String? {
        return colorMap[name]?.image
    }

    override fun hasHex(name: String): Boolean {
        if (colorMap.contains(name)) {
            return colorMap[name]?.hex != null
        }
        return false
    }

    override fun hasImage(name: String): Boolean {
        if (colorMap.contains(name)) {
            return colorMap[name]?.image != null
        }
        return false
    }

    override fun setColorValues(colorValues: List<ColorValue>) {
        colorValues.forEach { colorValue ->
            colorValue.name?.let { name ->
                colorMap.put(name, colorValue)
            }
        }
    }


}