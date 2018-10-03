package com.sembozdemir.altayersemih.network.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListResponse(

        @field:Json(name = "facets")
        val facets: Facets? = null,

        @field:Json(name = "page")
        val page: Int? = null,

        @field:Json(name = "categoryName")
        val categoryName: String? = null,

        @field:Json(name = "hitsPerPage")
        val hitsPerPage: String? = null,

        @field:Json(name = "pagination")
        val pagination: Pagination? = null,

        @field:Json(name = "hits")
        val hits: List<Hit> = emptyList()

) : Parcelable