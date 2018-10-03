package com.sembozdemir.altayersemih.network.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AttributeSpecificProperties(

        @field:Json(name = "swatchImage")
        val swatchImage: String? = null,

        @field:Json(name = "productThumbnail")
        val productThumbnail: String? = null,

        @field:Json(name = "hex")
        val hex: String? = null

) : Parcelable