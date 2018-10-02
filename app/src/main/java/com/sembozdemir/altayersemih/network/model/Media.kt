package com.sembozdemir.altayersemih.network.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Media(

        @Json(name = "videoUrl")
        val videoUrl: String? = null,

        @Json(name = "src")
        val src: String? = null,

        @Json(name = "mediaType")
        val mediaType: String? = null,

        @Json(name = "position")
        val position: Int? = null

) : Parcelable