package com.sembozdemir.altayersemih.network.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pagination(

        @Json(name = "totalHits")
        val totalHits: Int? = null,

        @Json(name = "totalPages")
        val totalPages: Int? = null

) : Parcelable
