package com.cccm.crowingrooster.network.body

import com.squareup.moshi.Json

data class Company(
    @Json(name = "nombre_empresa")
    var company: String
)