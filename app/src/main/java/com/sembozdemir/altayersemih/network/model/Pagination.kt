package com.sembozdemir.altayersemih.network.model

import com.squareup.moshi.Json

data class Pagination(

        @Json(name = "totalHits")
        val totalHits: Int? = null,

        @Json(name = "totalPages")
        val totalPages: Int? = null
)
