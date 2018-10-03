package com.sembozdemir.altayersemih.network.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Media(

        @field:Json(name = "videoUrl")
        val videoUrl: String? = null,

        @field:Json(name = "src")
        val src: String? = null,

        @field:Json(name = "mediaType")
        val mediaType: String? = null,

        @field:Json(name = "position")
        val position: Int? = null

) : Parcelable