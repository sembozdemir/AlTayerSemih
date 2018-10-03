package com.sembozdemir.altayersemih.network.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Color(

        @field:Json(name = "name")
        val name: String? = null,

        @field:Json(name = "value")
        val colorValue: List<ColorValue> = emptyList()

) : Parcelable