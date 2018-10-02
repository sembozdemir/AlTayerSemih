package com.sembozdemir.altayersemih.network.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Hit(

        @Json(name = "productId")
        val productId: Long? = null,

        @Json(name = "sku")
        val sku: String? = null,

        @Json(name = "name")
        val name: String? = null,

        @Json(name = "designerCategoryName")
        val designerCategoryName: String? = null,

        @Json(name = "slug")
        val slug: String? = null,

        @Json(name = "price")
        val price: Double? = null,

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "thumbnail")
        val thumbnail: String? = null

) : Parcelable
