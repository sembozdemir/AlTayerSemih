package com.sembozdemir.altayersemih.network.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OptionsItem(

        @Json(name = "attributeSpecificProperties")
        val attributeSpecificProperties: AttributeSpecificProperties? = null,

        @Json(name = "simpleProductSkus")
        val simpleProductSkus: List<String> = emptyList(),

        @Json(name = "optionId")
        val optionId: Int? = null,

        @Json(name = "label")
        val label: String? = null,

        @Json(name = "isInStock")
        val isInStock: Boolean? = null

) : Parcelable