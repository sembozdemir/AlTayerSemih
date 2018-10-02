package com.sembozdemir.altayersemih.network.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ConfigurableAttribute(

        @Json(name = "code")
        val code: String? = null,

        @Json(name = "options")
        val options: List<OptionsItem>? = null

) : Parcelable