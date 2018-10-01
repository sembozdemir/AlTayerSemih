package com.sembozdemir.altayersemih.network.model

import com.squareup.moshi.Json

data class AttributeSpecificProperties(

        @Json(name = "swatchImage")
        val swatchImage: String? = null,

        @Json(name = "productThumbnail")
        val productThumbnail: String? = null,

        @Json(name = "hex")
        val hex: String? = null
)