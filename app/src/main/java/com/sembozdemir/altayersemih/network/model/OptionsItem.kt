package com.sembozdemir.altayersemih.network.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OptionsItem(

        @field:Json(name = "attributeSpecificProperties")
        val attributeSpecificProperties: AttributeSpecificProperties? = null,

        @field:Json(name = "simpleProductSkus")
        val simpleProductSkus: List<String> = emptyList(),

        @field:Json(name = "optionId")
        val optionId: Int? = null,

        @field:Json(name = "label")
        val label: String? = null,

        @field:Json(name = "isInStock")
        val isInStock: Boolean? = null

) : Parcelable