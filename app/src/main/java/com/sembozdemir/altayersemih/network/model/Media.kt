package com.sembozdemir.altayersemih.network.model

import com.squareup.moshi.Json

data class Media(

        @Json(name = "videoUrl")
        val videoUrl: String? = null,

        @Json(name = "src")
        val src: String? = null,

        @Json(name = "mediaType")
        val mediaType: String? = null,

        @Json(name = "position")
        val position: Int? = null
)