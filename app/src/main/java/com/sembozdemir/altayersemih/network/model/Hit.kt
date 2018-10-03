package com.sembozdemir.altayersemih.network.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Hit(

        @field:Json(name = "productId")
        val productId: Long? = null,

        @field:Json(name = "sku")
        val sku: String? = null,

        @field:Json(name = "name")
        val name: String? = null,

        @field:Json(name = "designerCategoryName")
        val designerCategoryName: String? = null,

        @field:Json(name = "slug")
        val slug: String? = null,

        @field:Json(name = "price")
        val price: Double? = null,

        @field:Json(name = "image")
        val image: String? = null,

        @field:Json(name = "thumbnail")
        val thumbnail: String? = null

) : Parcelable
