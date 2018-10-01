package com.sembozdemir.altayersemih.network.model

import com.squareup.moshi.Json

data class OptionsItem(

        @Json(name = "attributeSpecificProperties")
        val attributeSpecificProperties: AttributeSpecificProperties? = null,

        @Json(name = "simpleProductSkus")
        val simpleProductSkus: List<String>? = null,

        @Json(name = "optionId")
        val optionId: Int? = null,

        @Json(name = "label")
        val label: String? = null,

        @Json(name = "isInStock")
        val isInStock: Boolean? = null
)