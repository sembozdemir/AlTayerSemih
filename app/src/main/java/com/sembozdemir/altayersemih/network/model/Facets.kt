package com.sembozdemir.altayersemih.network.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Facets(

        @field:Json(name = "color")
        val color: Color? = null

) : Parcelable