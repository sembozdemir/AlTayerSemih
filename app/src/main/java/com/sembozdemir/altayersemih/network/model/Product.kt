package com.sembozdemir.altayersemih.network.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(

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
        val thumbnail: String? = null,

        @field:Json(name = "media")
        val media: List<Media> = listOf(),

        @field:Json(name = "configurableAttributes")
        val configurableAttributes: List<ConfigurableAttribute> = emptyList(),

        @field:Json(name = "color")
        val color: String? = null,

        @field:Json(name = "sizeCode")
        val sizeCode: String? = null,

        @field:Json(name = "description")
        val description: String? = null,

        @field:Json(name = "sizeAndFit")
        val sizeAndFit: String? = null,

        @field:Json(name = "sameColorSiblings")
        val sameColorSiblings: List<String> = emptyList()

) : Parcelable