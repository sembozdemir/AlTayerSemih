package com.sembozdemir.altayersemih.network.model

import com.squareup.moshi.Json

data class ConfigurableAttribute(

        @Json(name = "code")
        val code: String? = null,

        @Json(name = "options")
        val options: List<OptionsItem>? = null
)