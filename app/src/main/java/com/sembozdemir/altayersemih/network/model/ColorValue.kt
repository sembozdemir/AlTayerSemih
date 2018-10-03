package com.sembozdemir.altayersemih.network.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ColorValue(

        @field:Json(name = "name")
        val name: String? = null,

        @field:Json(name = "hex")
        val hex: String? = null,

        @field:Json(name = "image")
        val image: String? = null

) : Parcelable