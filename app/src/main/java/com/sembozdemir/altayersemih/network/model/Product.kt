package com.sembozdemir.altayersemih.network.model

import com.squareup.moshi.Json

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
        val media: List<Media>? = null,

        @Json(name = "configurableAttributes")
        val configurableAttributes: List<ConfigurableAttribute>? = null


)