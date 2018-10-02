package com.sembozdemir.altayersemih.network.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AttributeSpecificProperties(

        @Json(name = "swatchImage")
        val swatchImage: String? = null,

        @Json(name = "productThumbnail")
        val productThumbnail: String? = null,

        @Json(name = "hex")
        val hex: String? = null

) : Parcelable