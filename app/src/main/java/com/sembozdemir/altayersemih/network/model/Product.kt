package com.sembozdemir.altayersemih.network.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(

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
        val thumbnail: String? = null,

        @Json(name = "media")
        val media: List<Media> = listOf(),

        @Json(name = "configurableAttributes")
        val configurableAttributes: List<ConfigurableAttribute> = emptyList(),

        @Json(name = "color")
        val color: String? = null,

        @Json(name = "sizeCode")
        val sizeCode: String? = null,

        @Json(name = "description")
        val description: String? = null,

        @Json(name = "sizeAndFit")
        val sizeAndFit: String? = null,

        @Json(name = "sameColorSiblings")
        val sameColorSiblings: List<String> = emptyList()

) : Parcelable