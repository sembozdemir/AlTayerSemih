package com.sembozdemir.altayersemih.network.model

import com.squareup.moshi.Json

data class ListResponse(

        @Json(name = "page")
        val page: Int? = null,

        @Json(name = "categoryName")
        val categoryName: String? = null,

        @Json(name = "hitsPerPage")
        val hitsPerPage: String? = null,

        @Json(name = "pagination")
        val pagination: Pagination? = null,

        @Json(name = "hits")
        val hits: List<Hit>? = null
)